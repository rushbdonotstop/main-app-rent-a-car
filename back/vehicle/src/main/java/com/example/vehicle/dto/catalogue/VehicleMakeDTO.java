package com.example.vehicle.dto.catalogue;

public class VehicleMakeDTO {

    private Long id;
    private String value;

    public VehicleMakeDTO() {
    }

    public VehicleMakeDTO(String value) {
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
