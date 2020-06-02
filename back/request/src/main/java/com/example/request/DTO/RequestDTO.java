package com.example.request.DTO;

import com.example.request.model.Bundle;
import com.example.request.model.Request;

import java.util.List;

public class RequestDTO {
    List<Request> requests;
    List<Bundle> bundles;

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public List<Bundle> getBundles() {
        return bundles;
    }

    public void setBundles(List<Bundle> bundles) {
        this.bundles = bundles;
    }
}
