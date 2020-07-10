package com.example.request.dto.vehicle.builder;

import com.example.request.dto.vehicle.Vehicle;
import com.example.request.dto.vehicle.VehicleImage;

import java.time.LocalDateTime;

public class VehicleBuilder {
    private long id;
    private int mileage;
    private int mileageLimit;
    private boolean collisionProtection;
    private int childrenSeats;
    private VehicleImage image;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long fuelTypeId;
    private Long makeId;
    private Long modelId;
    private Long styleId;
    private Long transmissionId;
    private Long locationId;
    private Long userId;

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

    public VehicleBuilder setPicture(VehicleImage image) {
        this.image = image;
        return this;
    }

    public VehicleBuilder setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public VehicleBuilder setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public VehicleBuilder setFuelTypeId(Long fuelTypeId) {
        this.fuelTypeId = fuelTypeId;
        return this;
    }

    public VehicleBuilder setMakeId(Long makeId) {
        this.makeId = makeId;
        return this;
    }

    public VehicleBuilder setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }

    public VehicleBuilder setStyleId(Long styleId) {
        this.styleId = styleId;
        return this;
    }

    public VehicleBuilder setTransmissionId(Long transmissionId) {
        this.transmissionId = transmissionId;
        return this;
    }

    public VehicleBuilder setLocationId(Long locationId) {
        this.locationId = locationId;
        return this;
    }

    public VehicleBuilder setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public Vehicle createVehicle() {
        return new Vehicle(id, mileage, mileageLimit, collisionProtection, childrenSeats, startDate, endDate, fuelTypeId, makeId, modelId, styleId, transmissionId, locationId, userId, image);
    }
}
