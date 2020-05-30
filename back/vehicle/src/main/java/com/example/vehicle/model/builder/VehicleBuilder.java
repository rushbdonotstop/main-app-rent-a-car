package com.example.vehicle.model.builder;

import com.example.vehicle.model.Vehicle;

import java.util.Date;

public class VehicleBuilder {
    private long id;
    private int mileage;
    private int mileageLimit;
    private boolean collisionProtection;
    private int childrenSeats;
    private int picture;
    private Date startDate;
    private Date endDate;
    private Long vehicleFuelTypeId;
    private Long vehicleMakeId;
    private Long vehicleModelId;
    private Long vehicleStypeId;
    private Long vehicleTransmissionId;

    public VehicleBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public VehicleBuilder setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public VehicleBuilder setMileageLimit(int mileageLimit) {
        this.mileageLimit = mileageLimit;
        return this;
    }

    public VehicleBuilder setCollisionProtection(boolean collisionProtection) {
        this.collisionProtection = collisionProtection;
        return this;
    }

    public VehicleBuilder setChildrenSeats(int childrenSeats) {
        this.childrenSeats = childrenSeats;
        return this;
    }

    public VehicleBuilder setPicture(int picture) {
        this.picture = picture;
        return this;
    }

    public VehicleBuilder setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public VehicleBuilder setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public VehicleBuilder setVehicleFuelTypeId(Long vehicleFuelTypeId) {
        this.vehicleFuelTypeId = vehicleFuelTypeId;
        return this;
    }

    public VehicleBuilder setVehicleMakeId(Long vehicleMakeId) {
        this.vehicleMakeId = vehicleMakeId;
        return this;
    }

    public VehicleBuilder setVehicleModelId(Long vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
        return this;
    }

    public VehicleBuilder setVehicleStypeId(Long vehicleStypeId) {
        this.vehicleStypeId = vehicleStypeId;
        return this;
    }

    public VehicleBuilder setVehicleTransmissionId(Long vehicleTransmissionId) {
        this.vehicleTransmissionId = vehicleTransmissionId;
        return this;
    }

    public Vehicle createVehicle() {
        return new Vehicle(id, mileage, mileageLimit, collisionProtection, childrenSeats, picture, startDate, endDate, vehicleFuelTypeId, vehicleMakeId, vehicleModelId, vehicleStypeId, vehicleTransmissionId);
    }
}