package com.example.vehicle.dto.location;

public class StreetDTO {

    private Long id;
    private String value;

    public StreetDTO() {
    }

    public StreetDTO(String value) {
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
        return "Street{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
