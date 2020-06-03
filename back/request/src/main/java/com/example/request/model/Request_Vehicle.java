package com.example.request.model;

import javax.persistence.*;

@Entity
public class Request_Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Request request;

    @Column(nullable = false)
    private Long vehicleId;

    public Request_Vehicle(Request request, Long vehicleId) {
        this.request = request;
        this.vehicleId = vehicleId;
    }

    public Request_Vehicle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Request_Vehicle{" +
                "id=" + id +
                ", request=" + request +
                ", vehicleId=" + vehicleId +
                '}';
    }
}
