package com.example.vehicle.dto.catalogue.forsoap;

import javax.persistence.*;

@Entity
public class VehicleMake {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name="make", nullable = false, unique = true)
    private String value;

    public VehicleMake() {
    }

    public VehicleMake(String value) {
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

    public com.example.vehicle.xmlmodel.catalogue.vehicle_make.VehicleMake toXML(VehicleMake vehicleMake){
        com.example.vehicle.xmlmodel.catalogue.vehicle_make.VehicleMake vehicleMakeXML = new com.example.vehicle.xmlmodel.catalogue.vehicle_make.VehicleMake();
        vehicleMakeXML.setId(vehicleMake.getId());
        vehicleMakeXML.setValue(vehicleMake.getValue());
        return vehicleMakeXML;
    }


}
