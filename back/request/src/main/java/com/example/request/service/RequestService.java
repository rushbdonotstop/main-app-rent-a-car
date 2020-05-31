package com.example.request.service;

import com.example.request.DTO.RequestDTO;
import com.example.request.model.Request;
import com.example.request.model.Request_Vehicle;
import com.example.request.repository.RequestRepository;
import com.example.request.repository.Request_VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    Request_VehicleRepository request_vehicleRepository;

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
        for(RequestDTO requestdto : requests){
            if(requestdto.getRequest().getEndDate().compareTo(requestdto.getRequest().getStartDate())>=0 &&
                    requestdto.getRequest().getStartDate().compareTo(LocalDate.now())>=0) {
                Request newReq = requestRepository.saveAndFlush(requestdto.getRequest());
                for (Long vehicleId : requestdto.getVehicles()) {
                    request_vehicleRepository.save(new Request_Vehicle(vehicleId, newReq));
                }
            }else
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
}
