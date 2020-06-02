package com.example.request.service;

import com.example.request.DTO.PhysicalRequestDTO;
import com.example.request.DTO.RequestDTO;
import com.example.request.model.Request;
import com.example.request.model.Request_Vehicle;
import com.example.request.model.enums.Status;
import com.example.request.repository.RequestRepository;
import com.example.request.repository.Request_VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    Request_VehicleRepository request_vehicleRepository;

    public boolean areDatesValid(LocalDate startDate, LocalDate endDate) {
        if (endDate.compareTo(startDate) >= 0 &&
                startDate.compareTo(LocalDate.now()) >= 0)
            return true;
        else
            return false;
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

    public boolean addRequest(List<RequestDTO> requests) {
        for (RequestDTO requestdto : requests) {
            if (areDatesValid(requestdto.getRequest().getStartDate(), requestdto.getRequest().getEndDate())) {
                Request newReq = requestRepository.saveAndFlush(requestdto.getRequest());
                for (Long vehicleId : requestdto.getVehicles()) {
                    request_vehicleRepository.save(new Request_Vehicle(vehicleId, newReq));
                }
            } else
                return false;
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

    //TODO : optimize
    public boolean addPhysicalRenting(PhysicalRequestDTO request) {
        
        LocalDate startdate = request.getRequest().getStartDate();
        LocalDate enddate = request.getRequest().getEndDate();

        if (areDatesValid(startdate, enddate)) {

            List<Long> overlapingReqIds = requestRepository.overlapingRequests(startdate, enddate, request.getVehicleId());
            //List<Request> bundleMembers = requestRepository.bundleMembers(overlapingReqIds);

            for (Long requestId : overlapingReqIds) {
                Request req = requestRepository.findById(requestId).get();
                if (req != null) {
                    if (req.getStatus() != Status.RESERVED) {
                        req.setStatus(Status.CANCELLED);
                        requestRepository.save(req);
                    } else return false; //conflict
                }
            }

            request.getRequest().setStatus(Status.RESERVED);
            Request newReq = requestRepository.save(request.getRequest()); //Contains its new id
            request_vehicleRepository.save(new Request_Vehicle(request.getVehicleId(), newReq));
            return true;
        } else return false;
    }
}
