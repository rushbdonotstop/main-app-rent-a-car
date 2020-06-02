package com.example.request.DTO;

import com.example.request.model.Request;

public class PhysicalRequestDTO {
    private Request request;
    private Long vehicleId;

    @Override
    public String toString() {
        return request.toString();
    }

    public PhysicalRequestDTO() {
    }

    public PhysicalRequestDTO(Request request, Long vehicleId) {
        this.request = request;
        this.vehicleId = vehicleId;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
