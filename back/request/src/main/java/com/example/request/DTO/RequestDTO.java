package com.example.request.dto;

import com.example.request.model.Request;

import java.util.List;

public class RequestDTO {
    Request request;
    List<Long> vehicles;

    public RequestDTO() {
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public List<Long> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Long> vehicles) {
        this.vehicles = vehicles;
    }
}
