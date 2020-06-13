package com.example.vehicle.dto.catalogue;

import javax.persistence.*;

public class VehicleMake {

    private Long id;
    private String value;

    public VehicleMake() {
    }

    public VehicleMake(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "VehicleMake{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }


}
