package com.example.request.model;

import javax.persistence.*;

@Entity
public class Request_Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Request getRequest() {
        return request;
    }

    @ManyToOne
    private Request request;

    public Request_Vehicle(Long vehicleId, Request request) {
        this.vehicleId = vehicleId;
        this.request = request;
    }

    public Request getRequestId() {
        return request;
    }

    public Request_Vehicle() {
    }

    public void setRequestId(Request requestId) {
        this.request = requestId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Column(nullable = false)
    private Long vehicleId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
