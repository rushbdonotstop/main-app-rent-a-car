package com.example.request.service;

import com.example.request.DTO.BundleDTO;
import com.example.request.DTO.RequestDTO;
import com.example.request.DTO.RequestForFrontDTO;
import com.example.request.DTO.VehicleMainViewDTO;
import com.example.request.DTO.user.UserDTO;
import com.example.request.model.Bundle;
import com.example.request.model.Request;
import com.example.request.model.enums.Status;
import com.example.request.repository.BundleRepository;
import com.example.request.repository.RequestRepository;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    BundleRepository bundleRepository;

    public boolean areDatesValid(LocalDateTime startDate, LocalDateTime endDate) {
        if(startDate==null || endDate==null)
            return false;
        if (endDate.compareTo(startDate) >= 0 &&
                startDate.compareTo(LocalDateTime.now()) >= 0)
            return true;
        else {
            System.err.println("Invalid dates");
            return false;
        }
    }

    public boolean isBundleValid(Bundle bundle) {
        List<Request> requests = bundle.getRequests();
        System.out.println(requests);
        if (requests.size() <= 1) {
            System.err.println("Bundle cannot contain one vehicle only");
            return false;
        }
        Long ownerId = requests.get(0).getOwnerId();
        for (Request request : requests) {
            if (!request.getOwnerId().equals(ownerId)) {
                System.out.println(request.getOwnerId());
                System.err.println("All bundle members must have the same owner");
                return false;
            }

        }
        return true;
    }

    public List<Request> findAll() {
        return this.requestRepository.findAll();
    }

    public Request update(Long id, Request newRequest) {
        Request request = requestRepository.findById(id).get();

        if (request != null) {
            request = newRequest;
            requestRepository.save(request);
            return request;
        }
        return null;
    }

    public boolean addRequest(RequestDTO requests) {
        //-----------------checks------------------

        for (Bundle bundle : requests.getBundles()) {
            if (!isBundleValid(bundle))
                return false;
            for (Request request : bundle.getRequests()) {
                if (!(areDatesValid(request.getStartDate(), request.getEndDate()) && request.getUserId() != null))
                    return false;
            }
        }
        for (Request request : requests.getRequests()) {
            if (!(areDatesValid(request.getStartDate(), request.getEndDate()) && request.getUserId() != null))
                return false;
        }

        //-------------------------------------------

        for (Bundle bundle : requests.getBundles()) {
            Bundle newBundle = bundleRepository.saveAndFlush(new Bundle(requests.getRequests())); //contains Id
            for (Request request : bundle.getRequests()) {
                request.setBundle(newBundle);
                request.setStatus(Status.PENDING);
                requestRepository.saveAndFlush(request);
            }

        }
        for (Request request : requests.getRequests()) {
            request.setStatus(Status.PENDING);
            requestRepository.saveAndFlush(request);
        }
        return true;
    }

    public Boolean delete(Long id) {
        Request request = requestRepository.findById(id).get();
        if (request != null) {
            requestRepository.delete(request);
            return true;
        }
        return false;
    }

    public boolean addPhysicalRenting(Request request) {

        if(request.getVehicleId()==null)
            return false;

        LocalDateTime startdate = request.getStartDate();
        LocalDateTime enddate = request.getEndDate();

        if (areDatesValid(startdate, enddate)) {

            List<Request> overlappingReqs = requestRepository.overlapingRequests(startdate, enddate, request.getVehicleId());
            System.out.println(overlappingReqs);

            for (Request req : overlappingReqs) {
                if (req.getBundle() == null) {
                    //Cancel requests which are not part of a bundle
                    if (req.getStatus() != Status.RESERVED) {
                        req.setStatus(Status.CANCELLED);
                        requestRepository.save(req);
                    } else return false; //conflict
                } else {
                    //Cancel members of a bundle for each conflicting request that is in a bundle
                    List<Request> bundleMembers = requestRepository.bundleMembers(req.getBundle());
                    for (Request reqInBundle : bundleMembers) {
                        if (reqInBundle.getStatus() != Status.RESERVED) {
                            reqInBundle.setStatus(Status.CANCELLED);
                            requestRepository.save(req);
                        } else return false; //conflict
                    }
                }

            }

            request.setStatus(Status.RESERVED);
            request.setBundle(null);
            requestRepository.save(request);
            return true;
        } else return false;
    }

    public List<Request> getAllRequestsForOwner (Long ownerId) {
        List<Request> requestList = requestRepository.findAll();
        List<Request> newRequestList = new ArrayList<>();
        for (Request request : requestList) {
            if (request.getOwnerId().equals(ownerId)) {
                newRequestList.add(request);
            }
        }
        return newRequestList;
    }

    public List<Request> getAllRequestsForUser (Long userId) {
        List<Request> requestList = requestRepository.findAll();
        List<Request> newRequestList = new ArrayList<>();
        for (Request request : requestList) {
            if (request.getUserId().equals(userId)) {
                newRequestList.add(request);
            }
        }
        return newRequestList;
    }
//
//    public List<Request> getAllRequestsByUser (Long userId) {
//        List<Request> requestsList = requestRepository.findAll();
//        List<Request> newRequestsList = new ArrayList<>();
//        for (Request request : requestsList) {
//            if (request.getUserId().equals(userId)) {
//                newRequestsList.add(request);
//            }
//        }
//        return newRequestsList;
//    }


    public List<RequestForFrontDTO> getDTOListForOwner (List<Request> requestsList, List<UserDTO> userDTOList, List<VehicleMainViewDTO> vehiclesList) {
        List<RequestForFrontDTO> newDTOList = new ArrayList<>();

        for (Request request : requestsList) {
            RequestForFrontDTO dto = new RequestForFrontDTO();
            dto.setId(request.getId());
            dto.setTotalCost(request.getTotalCost());
            dto.setStartDate(request.getStartDate());
            dto.setEndDate(request.getEndDate());
            dto.setStatus(request.getStatus());
            for (UserDTO user : userDTOList) {
                if (user.getId().equals(request.getUserId())) {
                    dto.setUsername(user.getUsername());
                }
            }
            //SETTING VEHICLE MAKE AND MODEL FOR REQUEST DTO
            for (VehicleMainViewDTO vehicle : vehiclesList) {
                if (vehicle.getId().equals(request.getVehicleId())) {
                    dto.setMakePlusModel(vehicle.getMake() + " " + vehicle.getModel());
                    break;
                }
            }
            dto.setBundleId(request.getBundle().getId());
            newDTOList.add(dto);

        }
        return newDTOList;
    }

    public List<RequestForFrontDTO> getDTOListForUser (List<Request> requestsList, List<UserDTO> userDTOList, List<VehicleMainViewDTO> vehiclesList) {
        List<RequestForFrontDTO> newDTOList = new ArrayList<>();

        for (Request request : requestsList) {
            RequestForFrontDTO dto = new RequestForFrontDTO();
            dto.setId(request.getId());
            dto.setTotalCost(request.getTotalCost());
            dto.setStartDate(request.getStartDate());
            dto.setEndDate(request.getEndDate());
            dto.setStatus(request.getStatus());
            for (UserDTO user : userDTOList) {
                if (user.getId().equals(request.getOwnerId())) {
                    dto.setUsername(user.getUsername());
                }
            }
            //SETTING VEHICLE MAKE AND MODEL FOR REQUEST DTO
            for (VehicleMainViewDTO vehicle : vehiclesList) {
                if (vehicle.getId().equals(request.getVehicleId())) {
                    dto.setMakePlusModel(vehicle.getMake() + " " + vehicle.getModel());
                    break;
                }
            }
            dto.setBundleId(request.getBundle().getId());
            newDTOList.add(dto);

        }
        return newDTOList;
    }

    public List<BundleDTO> getBundles(List<RequestForFrontDTO> requestList) {
        TreeSet<Long> bundleIdSet = new TreeSet<>();
        List<BundleDTO> bundleList = new ArrayList<>();
        for (RequestForFrontDTO request : requestList) {
            bundleIdSet.add(request.getBundleId());
        }

        for (Long bundleId : bundleIdSet) {
            BundleDTO dto = new BundleDTO();
            for (RequestForFrontDTO request : requestList) {
                System.out.println("VELICINA REQUEST LISTE JE: " + requestList.size());
                dto.setId(bundleId);
                if (request.getBundleId().equals(bundleId)) {
                    dto.getRequestsList().add(request);
                    dto.setUsername(request.getUsername());
                }
                float totalPrice = 0;
                for (RequestForFrontDTO request1 : dto.getRequestsList()) {
                    totalPrice += request1.getTotalCost();
                }
                dto.setTotalCost(totalPrice);
            }
            bundleList.add(dto);
        }
        return bundleList;
    }

    public boolean changeRequestStatusToReserved(Long requestId) {
        Request req = requestRepository.findById(requestId).get();
        List<Request> requestList = requestRepository.findAll();

        for (Request request : requestList) {
            if (request.getId().equals(req.getId())) {
                continue;
            }
            if (request.getVehicleId().equals(req.getVehicleId()) && request.getStatus().equals(Status.RESERVED)) {
                return false;
            }
        }

        req.setStatus(Status.RESERVED);
        return true;
    }

    public boolean changeBundleStatusToReserved(Long bundleId) {
        List<Request> requestList = requestRepository.findAll();
        for (Request request : requestList) {
            if (request.getBundle().getId().equals(bundleId)) {
                 if (!changeRequestStatusToReserved(request.getId())) {
                     return false;
                 }
            }
        }
        return true;
    }

    public boolean changeRequestStatusToPaid(Long requestId) {
        Request req = requestRepository.findById(requestId).get();
        List<Request> requestList = requestRepository.findAll();

        for (Request request : requestList) {
            if (request.getId().equals(req.getId())) {
                continue;
            }

            if (request.getVehicleId().equals(req.getVehicleId()) && request.getStatus().equals(Status.PAID) && (request.getStartDate().isBefore(req.getEndDate()) && req.getStartDate().isBefore(request.getEndDate()))) {
                return false;
            }

            if (request.getVehicleId().equals(req.getVehicleId()) && request.getStatus().equals(Status.PENDING) && (request.getStartDate().isBefore(req.getEndDate()) && req.getStartDate().isBefore(request.getEndDate()))) {
                request.setStatus(Status.CANCELLED);
            }
        }
        return true;
    }
    //RETURNS TRUE IF ALL REQUESTS ARE CHANGED TO STATUS PAID AND, ALL OTHER REQUEST ARE CHANGED TO CANCELED IF DATES OVERLAP
    //RETURN FALSE IF THERE IS ALREADY PAID REQUEST WITH DATE THAT OVERLAP
    public boolean changeBundleStatusToPaid(Long bundleId) {
        boolean value = true;
        List<Request> requestList = requestRepository.findAll();
        for (Request request : requestList) {
            if (request.getBundle().getId().equals(bundleId)) {
                value = changeRequestStatusToPaid(request.getId());
            }
        }

        return value;
    }

    public boolean changeRequestStatusToCancelled(Long requestId) {
        Request req = requestRepository.findById(requestId).get();
        req.setStatus(Status.CANCELLED);
        return true;
    }

    public boolean ChangeBundleStatusToCancelled(Long bundleId) {
        List<Request> requestList = requestRepository.findAll();
        for (Request request : requestList) {
            boolean value = changeRequestStatusToCancelled(request.getId());
        }
        return true;
    }

    public Boolean canUserPostReview(Long vehicleId, Long userId) {
        try{
            List<Request> requests = requestRepository.findAllByVehicleIdAndUserIdAndStatus(vehicleId, userId, Status.PAID);

            for(Request r : requests){
                if(r.getEndDate().isBefore(LocalDateTime.now())){
                    return true;
                }
            }
            return false;
        }
        catch(Exception e){
            System.out.println("Exception in canUserPostReview");
        }
        return false;
    }
}
