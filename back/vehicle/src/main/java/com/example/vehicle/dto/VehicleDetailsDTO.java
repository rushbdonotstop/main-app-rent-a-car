package com.example.vehicle.dto;

import com.example.vehicle.dto.builders.VehicleDetailsDTOBuilder;
import com.example.vehicle.model.Vehicle;

public class VehicleDetailsDTO {

    private Long vehicleId;
    private Long fuelTypeId;
    private Long styleId;
    private Long transmissionId;
    private Long modelId;
    private Long makeId;
    private Long locationId;
    private Long ownerId;

    public VehicleDetailsDTO() {
    }

    public VehicleDetailsDTO(Long vehicleId, Long fuelTypeId, Long styleId, Long transmissionId, Long modelId, Long makeId, Long locationId, Long ownerId) {
        this.vehicleId = vehicleId;
        this.fuelTypeId = fuelTypeId;
        this.styleId = styleId;
        this.transmissionId = transmissionId;
        this.modelId = modelId;
        this.makeId = makeId;
        this.locationId = locationId;
        this.ownerId = ownerId;
    }

    public VehicleDetailsDTO(Vehicle vehicle) {
        this.vehicleId = vehicle.getId();
        this.fuelTypeId = vehicle.getFuelTypeId();
        this.styleId = vehicle.getStyleId();
        this.transmissionId = vehicle.getTransmissionId();
        this.modelId = vehicle.getModelId();
        this.makeId = vehicle.getMakeId();
        this.locationId = vehicle.getLocationId();
        this.ownerId = vehicle.getUserId();
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getFuelTypeId() {
        return fuelTypeId;
    }

    public void setFuelTypeId(Long fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
    }

    public Long getStyleId() {
        return styleId;
    }

    public void setStyleId(Long styleId) {
        this.styleId = styleId;
    }

    public Long getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(Long transmissionId) {
        this.transmissionId = transmissionId;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getMakeId() {
        return makeId;
    }

    public void setMakeId(Long makeId) {
        this.makeId = makeId;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "VehicleDetailsDTO{" +
                "vehicleId=" + vehicleId +
                ", fuelTypeId=" + fuelTypeId +
                ", styleId=" + styleId +
                ", transmissionId=" + transmissionId +
                ", modelId=" + modelId +
                ", makeId=" + makeId +
                ", locationId=" + locationId +
                ", ownerId=" + ownerId +
                '}';
    }
}
