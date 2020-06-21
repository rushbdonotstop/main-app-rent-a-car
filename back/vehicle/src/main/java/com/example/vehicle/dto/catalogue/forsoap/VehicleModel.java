package com.example.vehicle.dto.catalogue.forsoap;

import javax.persistence.*;

@Entity
public class VehicleModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="model", nullable = false, unique = true)
    private String value;

    @ManyToOne
    @JoinColumn(name="make_id", referencedColumnName = "id", nullable = false)
    private VehicleMake vehicleMake;

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

    public VehicleMake getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(VehicleMake vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    @Override
    public String toString() {
        return "VehicleModel{" +
                "id=" + id +
                ", value='" + value + '\'' +
                ", vehicleMake=" + vehicleMake +
                '}';
    }

    public com.example.vehicle.xmlmodel.catalogue.vehicle_model.VehicleModel toXML(VehicleModel vehicleModel){
        com.example.vehicle.xmlmodel.catalogue.vehicle_model.VehicleModel vehicleModelXML = new com.example.vehicle.xmlmodel.catalogue.vehicle_model.VehicleModel();
        vehicleModelXML.setId(vehicleModel.getId());
        vehicleModelXML.setValue(vehicleModel.getValue());
        vehicleModelXML.setVehicleMake(vehicleModel.getVehicleMake().toXML(vehicleModel.getVehicleMake()));
        return vehicleModelXML;
    }
}
