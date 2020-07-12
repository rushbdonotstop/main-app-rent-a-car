package com.example.request.service;

import com.example.request.dto.BundleDTO;
import com.example.request.dto.RequestDTO;
import com.example.request.dto.RequestForFrontDTO;
import com.example.request.dto.VehicleMainViewDTO;
import com.example.request.dto.user.UserDTO;
import com.example.request.model.Bundle;
import com.example.request.model.Request;
import com.example.request.model.enums.Status;
import com.example.request.repository.BundleRepository;
import com.example.request.repository.ReportRepository;
import com.example.request.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    BundleRepository bundleRepository;

    @Autowired
    ReportRepository reportRepository;

    public boolean areDatesValid(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null)
            return false;

        LocalDateTime twoDaysFromNow = LocalDateTime.now().plusDays(2);

        if (endDate.compareTo(startDate) >= 0 &&
                startDate.compareTo(twoDaysFromNow) >= 0)
            return true;
        else {
            System.err.println("Invalid dates");
            return false;
        }
    }

    //perform checks
    public boolean isRentingAllowed(RequestDTO requestDTO) {

        for (Request request : requestDTO.getRequests()) {
            if (request.getOwnerId() == null || request.getUserId() == null)
                return false;
            //owner cannot rent his own car
            if (request.getOwnerId().equals(request.getUserId())) {
                System.err.println("Owner cannot rent his own car");
                return false;
            }
            if (!(areDatesValid(request.getStartDate(), request.getEndDate()) && request.getUserId() != null))
                return false;
        }
        for (Bundle bundle : requestDTO.getBundles()) {
            if (!isBundleValid(bundle))
                return false;
            for (Request request : requestDTO.getRequests()) {
                if (request.getOwnerId() == null || request.getUserId() == null)
                    return false;
                //owner cannot rent his own car
                if (request.getOwnerId().equals(request.getUserId())) {
                    System.err.println("Owner cannot rent his own car");
                    return false;
                }
                if (!(areDatesValid(request.getStartDate(), request.getEndDate()) && request.getUserId() != null))
                    return false;
            }
        }
        return true;
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
    @Transactional
    public boolean addRequest(RequestDTO requests) {

        if (!isRentingAllowed(requests))
            return false;
        System.out.println("VELICINA LISTE BUNDLE-OVA: " + requests.getBundles().size());
        for (Bundle bundle : requests.getBundles()) {

            System.err.println("BUNDLE JE: \t" + bundle.toString());
            Bundle b = new Bundle(bundle.getRequests());
            System.err.println("posle kreiranja bundlea" + b.toString());
            Bundle newBundle = bundleRepository.save(b);

            for (Request request : bundle.getRequests()) {
                LocalDateTime ldt = LocalDateTime.now();
                request.setBundle(newBundle);
                request.setStatus(Status.PENDING);
                request.setTimeOfCreation(ldt);
                requestRepository.save(request);
            }

        }
        for (Request request : requests.getRequests()) {
            LocalDateTime ldt = LocalDateTime.now();
            request.setStatus(Status.PENDING);
            request.setTimeOfCreation(ldt);
            requestRepository.save(request);
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

        if (request.getVehicleId() == null)
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

    public List<Request> getAllRequestsForOwner(Long ownerId) {
        List<Request> requestList = requestRepository.findAll();
        List<Request> newRequestList = new ArrayList<>();
        for (Request request : requestList) {
            if (request.getOwnerId().equals(ownerId) && request.getBundle() != null) {
                newRequestList.add(request);
            }
        }
        return newRequestList;
    }

    //RETURNING LIST OF REQUESTS THAT ARE NOT IN THE BUNDLE!!!
    public List<Request> getSingleRequestsForOwner(Long ownerId) {
        List<Request> requestList = requestRepository.findAll();
        List<Request> newRequestList = new ArrayList<>();
        for (Request request : requestList) {
            if (request.getOwnerId().equals(ownerId) && request.getBundle() == null) {
                newRequestList.add(request);
            }
        }
        return newRequestList;
    }

    public List<Request> getAllRequestsForUser(Long userId) {
        List<Request> requestList = requestRepository.findAll();
        List<Request> newRequestList = new ArrayList<>();
        for (Request request : requestList) {
            if (request.getUserId().equals(userId) && request.getBundle() != null) {
                newRequestList.add(request);
            }
        }
        return newRequestList;
    }

    //RETURNING LIST OF REQUESTS THAT ARE NOT IN THE BUNDLE!!!
    public List<Request> getSingleRequestsForUser(Long userId) {
        List<Request> requestList = requestRepository.findAll();
        List<Request> newRequestList = new ArrayList<>();
        for (Request request : requestList) {
            if (request.getUserId().equals(userId) && request.getBundle() == null) {
                newRequestList.add(request);
            }
        }
        return newRequestList;
    }

    public List<RequestForFrontDTO> getDTOListForOwner(List<Request> requestsList, List<UserDTO> userDTOList, List<VehicleMainViewDTO> vehiclesList) {
        List<RequestForFrontDTO> newDTOList = new ArrayList<>();

        for (Request request : requestsList) {
            RequestForFrontDTO dto = new RequestForFrontDTO();
            dto.setId(request.getId());
            dto.setTotalCost(request.getTotalCost());
            dto.setStartDate(request.getStartDate());
            dto.setEndDate(request.getEndDate());
            dto.setStatus(request.getStatus());
            dto.setVehicleId(request.getVehicleId());
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
            dto.setVehicleId((request.getVehicleId()));
            if (request.getBundle() != null) {
                dto.setBundleId(request.getBundle().getId());
            }
            newDTOList.add(dto);

        }
        return newDTOList;
    }

    public List<RequestForFrontDTO> getDTOListForUser(List<Request> requestsList, List<UserDTO> userDTOList, List<VehicleMainViewDTO> vehiclesList) {
        List<RequestForFrontDTO> newDTOList = new ArrayList<>();

        for (Request request : requestsList) {
            RequestForFrontDTO dto = new RequestForFrontDTO();
            dto.setId(request.getId());
            dto.setTotalCost(request.getTotalCost());
            dto.setStartDate(request.getStartDate());
            dto.setEndDate(request.getEndDate());
            dto.setVehicleId(request.getVehicleId());
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
            if (request.getBundle() != null) {
                dto.setBundleId(request.getBundle().getId());
            }
            newDTOList.add(dto);

        }
        return newDTOList;
    }

    public List<BundleDTO> getBundles(List<com.example.request.dto.RequestForFrontDTO> requestList) {
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
        req.setStatus(Status.PAID);
        requestRepository.save(req);
        return true;
    }

    //RETURNS TRUE IF ALL REQUESTS ARE CHANGED TO STATUS PAID AND, ALL OTHER REQUEST ARE CHANGED TO CANCELED IF DATES OVERLAP
    //RETURN FALSE IF THERE IS ALREADY PAID REQUEST WITH DATE THAT OVERLAP
    public boolean changeBundleStatusToPaid(Long bundleId) {
        boolean value = true;
        Bundle bundle = bundleRepository.findOneById(bundleId);
        List<Request> requestList = requestRepository.bundleMembers(bundle);
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
        requestRepository.save(req);
        return true;
    }

    public boolean changeBundleStatusToCancelled(Long bundleId) {
        Bundle bundle = bundleRepository.findOneById(bundleId);
        List<Request> requestList = requestRepository.bundleMembers(bundle);
        for (Request request : requestList) {
            boolean value = changeRequestStatusToCancelled(request.getId());
        }
        return true;
    }

    public Boolean canUserPostReview(Long vehicleId, Long userId) {
        try {
            List<Request> requests = requestRepository.findAllByVehicleIdAndUserIdAndStatus(vehicleId, userId, Status.PAID);

            for (Request r : requests) {
                if (r.getEndDate().isBefore(LocalDateTime.now())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Exception in canUserPostReview");
        }
        return false;
    }

    public List<Request> rentingFinishedRequests(Long ownerId) {
        List<Request> finishedRequests = this.requestRepository.rentingFinishedRequests(LocalDateTime.now());
        List<Request> nonReviewedRequests = new ArrayList<>();
        for (Request r : finishedRequests) {
            System.out.println("request already reviewed here:" + reportRepository.findByVehicleIdAndStartDateAndEndDate(r.getVehicleId(), r.getStartDate(), r.getEndDate()).size());
            if (reportRepository.findByVehicleIdAndStartDateAndEndDate(r.getVehicleId(), r.getStartDate(), r.getEndDate()).size() == 0 && r.getOwnerId().equals(ownerId)) {
                nonReviewedRequests.add(r);
            }
        }
        return nonReviewedRequests;
    }

    public List<Request> rentingFinishedRequestsInBundle(Long ownerId) {
        List<Request> requestsInBundles = new ArrayList<>();
        for (Request r : this.requestRepository.rentingFinishedRequestsInBundle(LocalDateTime.now()))
            if (r.getBundle() != null && reportRepository.findByVehicleIdAndStartDateAndEndDate(r.getVehicleId(), r.getStartDate(), r.getEndDate()).size() == 0 && r.getOwnerId().equals(ownerId)) {
                requestsInBundles.add(r);
            }
        return requestsInBundles;
    }

    public List<RequestForFrontDTO> getDTOList(List<Request> requestsList, List<UserDTO> userDTOList, List<VehicleMainViewDTO> vehiclesList) {
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
            dto.setVehicleId((request.getVehicleId()));
            if (request.getBundle() != null) {
                dto.setBundleId(request.getBundle().getId());
            }
            newDTOList.add(dto);

        }
        return newDTOList;
    }

    public Boolean canUserDelete(Long userId) {
        try {
            if(requestRepository.findByStatus(Status.PENDING).size() != 0){
                return false;
            }
            else{
                return true;
            }

        } catch (Exception e) {
        }
        return false;
    }

    public void startScheduledTask() {
        List<Request> requestList = requestRepository.findAll();
        LocalDateTime now = LocalDateTime.now();

        for (Request request : requestList) {
            if (request.getStatus().equals(Status.PENDING)) {
                long hours = ChronoUnit.HOURS.between(request.getTimeOfCreation(), now);
                System.err.println("Sati proslo: " + hours);
                if (hours > 23) {
                    request.setStatus(Status.CANCELLED);
                    requestRepository.save(request);
                }
            }
        }
    }
}
