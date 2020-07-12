package com.example.vehicle.dto.location;

import javax.persistence.*;

public class Street {

    private Long id;

    private String value;

    public Street() {
    }

    public Street(String value) {
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
