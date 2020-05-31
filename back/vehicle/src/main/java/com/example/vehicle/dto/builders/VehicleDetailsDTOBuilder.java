package com.example.vehicle.dto.builders;

import com.example.vehicle.dto.VehicleDetailsDTO;

public class VehicleDetailsDTOBuilder {
    private long id;
    private int mileage;
    private int mileageLimit;
    private boolean collisionProtection;
    private int childrenSeats;
    private String picturePath;
    private String fuelType;
    private String style;
    private String transmission;
    private String location;
    private String priceByMile;
    private String priceCollision;
    private String discount;
    private String numDaysDiscount;

    public VehicleDetailsDTOBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public VehicleDetailsDTOBuilder setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public VehicleDetailsDTOBuilder setMileageLimit(int mileageLimit) {
        this.mileageLimit = mileageLimit;
        return this;
    }

    public VehicleDetailsDTOBuilder setCollisionProtection(boolean collisionProtection) {
        this.collisionProtection = collisionProtection;
        return this;
    }

    public VehicleDetailsDTOBuilder setChildrenSeats(int childrenSeats) {
        this.childrenSeats = childrenSeats;
        return this;
    }

    public VehicleDetailsDTOBuilder setPicturePath(String picturePath) {
        this.picturePath = picturePath;
        return this;
    }

    public VehicleDetailsDTOBuilder setFuelType(String fuelType) {
        this.fuelType = fuelType;
        return this;
    }

    public VehicleDetailsDTOBuilder setStyle(String style) {
        this.style = style;
        return this;
    }

    public VehicleDetailsDTOBuilder setTransmission(String transmission) {
        this.transmission = transmission;
        return this;
    }

    public VehicleDetailsDTOBuilder setLocation(String location) {
        this.location = location;
        return this;
    }

    public VehicleDetailsDTOBuilder setPriceByMile(String priceByMile) {
        this.priceByMile = priceByMile;
        return this;
    }

    public VehicleDetailsDTOBuilder setPriceCollision(String priceCollision) {
        this.priceCollision = priceCollision;
        return this;
    }

    public VehicleDetailsDTOBuilder setDiscount(String discount) {
        this.discount = discount;
        return this;
    }

    public VehicleDetailsDTOBuilder setNumDaysDiscount(String numDaysDiscount) {
        this.numDaysDiscount = numDaysDiscount;
        return this;
    }

    public VehicleDetailsDTO createVehicleDetailsDTO() {
        return new VehicleDetailsDTO(id, mileage, mileageLimit, collisionProtection, childrenSeats, picturePath, fuelType, style, transmission, location, priceByMile, priceCollision, discount, numDaysDiscount);
    }
}
