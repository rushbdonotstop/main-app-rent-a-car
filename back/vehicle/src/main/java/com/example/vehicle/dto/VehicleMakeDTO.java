package com.example.vehicle.dto;

import javax.persistence.*;

public class VehicleMakeDTO {

    private Long id;
    private String value;

    public VehicleMakeDTO() {
    }

    public VehicleMakeDTO(Long id, String value) {
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
