package com.example.vehicle.dto.catalogue;

public class VehicleTransmissionDTO {

    private Long id;
    private String value;

    public VehicleTransmissionDTO() {
    }

    public VehicleTransmissionDTO(String value) {
        this.value = value;
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

    @Override
    public String toString() {
        return "VehicleTransmission{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
