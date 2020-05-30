package com.example.vehicle.dto.catalogue;

public class VehicleModelDTO {

    private Long id;
    private String value;

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

    public VehicleModelDTO() {
    }

    public VehicleModelDTO(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "VehicleModel{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
