package com.example.request.model;

import javax.persistence.*;

@Entity
public class RequestVehicle {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vehicle_id", nullable = false)
    private Long vehicleId;

    @ManyToOne
    @JoinColumn(name = "request_id", referencedColumnName = "id", nullable = false)
    private Request request;

    public RequestVehicle() {
    }

    public RequestVehicle(Long vehicleId, Request request) {
        this.vehicleId = vehicleId;
        this.request = request;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "RequestVehicle{" +
                "id=" + id +
                ", vehicleId=" + vehicleId +
                ", request=" + request +
                '}';
    }
}
