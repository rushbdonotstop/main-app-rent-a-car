package com.example.request.service;

import com.example.request.DTO.RequestDTO;
import com.example.request.model.Bundle;
import com.example.request.model.Request;
import com.example.request.model.enums.Status;
import com.example.request.repository.BundleRepository;
import com.example.request.repository.RequestRepository;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;

    @Autowired
    BundleRepository bundleRepository;

    public boolean areDatesValid(LocalDate startDate, LocalDate endDate) {
        if (endDate.compareTo(startDate) >= 0 &&
                startDate.compareTo(LocalDate.now()) >= 0)
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

        LocalDate startdate = request.getStartDate();
        LocalDate enddate = request.getEndDate();

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
}
