package com.example.vehicle.dto.location;

public class StateDTO {

    private Long id;
    private String value;

    public StateDTO() {
    }

    public StateDTO(String value) {
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
        return "State{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
