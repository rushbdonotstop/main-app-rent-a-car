package com.example.catalogue.model;

import javax.persistence.*;

@Entity
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="value", nullable = false, unique = true)
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

    public VehicleModel() {
    }

    public VehicleModel(String value) {
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
