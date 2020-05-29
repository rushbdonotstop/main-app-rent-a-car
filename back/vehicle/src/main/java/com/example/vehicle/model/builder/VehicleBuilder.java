package com.example.vehicle.model.builder;

import com.example.vehicle.model.Vehicle;

import java.util.Date;

public class VehicleBuilder {
    private int mileage;
    private int mileageLimit;
    private boolean collisionProtection;
    private int childrenSeats;
    private int picture;
    private Date startDate;
    private Date endDate;

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

    public Vehicle createVehicle() {
        return new Vehicle(mileage, mileageLimit, collisionProtection, childrenSeats, picture, startDate, endDate);
    }
}
