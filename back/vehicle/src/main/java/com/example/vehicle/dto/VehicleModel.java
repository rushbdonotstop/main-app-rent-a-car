package com.example.vehicle.dto;

import javax.persistence.*;


public class VehicleModel {

    private Long id;
    private String value;
    private Long vehicleMakeId;

    public VehicleModel(Long id, String value, Long vehicleMakeId) {
        this.id = id;
        this.value = value;
        this.vehicleMakeId = vehicleMakeId;
    }

    public VehicleModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getVehicleMakeId() {
        return vehicleMakeId;
    }

    public void setVehicleMakeId(Long vehicleMakeId) {
        this.vehicleMakeId = vehicleMakeId;
    }

    @Override
    public String toString() {
        return "VehicleModelDTO{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", vehicleMakeId=" + vehicleMakeId +
                '}';
    }
}
