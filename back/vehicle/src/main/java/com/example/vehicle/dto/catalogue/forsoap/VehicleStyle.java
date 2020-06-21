package com.example.vehicle.dto.catalogue.forsoap;

import javax.persistence.*;

@Entity
public class VehicleStyle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="style", nullable = false, unique = true)
    private String value;

    public VehicleStyle() {
    }

    public VehicleStyle(String value) {
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
        return "VehicleStyle{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }

    public com.example.vehicle.xmlmodel.catalogue.vehicle_style.VehicleStyle toXML(VehicleStyle vehicleStyle){
        com.example.vehicle.xmlmodel.catalogue.vehicle_style.VehicleStyle vehicleStyleXML = new com.example.vehicle.xmlmodel.catalogue.vehicle_style.VehicleStyle();
        vehicleStyleXML.setId(vehicleStyle.getId());
        vehicleStyleXML.setValue(vehicleStyle.getValue());
        return vehicleStyleXML;
    }
}
