package com.example.vehicle.dto;

import javax.persistence.*;

public class State {

    private Long id;

    private String value;

    public State() {
    }

    public State(String value) {
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
