package com.example.vehicle.dto;

import com.example.vehicle.dto.catalogue.VehicleFuelTypeDTO;
import com.example.vehicle.dto.catalogue.VehicleStyleDTO;
import com.example.vehicle.dto.catalogue.VehicleTransmissionDTO;
import com.example.vehicle.dto.location.LocationDTO;

public class VehicleDetailsDTO {

    private long id;
    private int mileage;
    private int mileageLimit;
    private boolean collisionProtection;
    private int childrenSeats;
    private String picturePath;
    private VehicleFuelTypeDTO fuelType;
    private VehicleStyleDTO style;
    private VehicleTransmissionDTO transmission;
    private LocationDTO location;

    public VehicleDetailsDTO() {
    }

    public VehicleDetailsDTO(long id, int mileage, int mileageLimit, boolean collisionProtection, int childrenSeats, String picturePath, VehicleFuelTypeDTO fuelType, VehicleStyleDTO style, VehicleTransmissionDTO transmission, LocationDTO location) {
        this.id = id;
        this.mileage = mileage;
        this.mileageLimit = mileageLimit;
        this.collisionProtection = collisionProtection;
        this.childrenSeats = childrenSeats;
        this.picturePath = picturePath;
        this.fuelType = fuelType;
        this.style = style;
        this.transmission = transmission;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getMileageLimit() {
        return mileageLimit;
    }

    public void setMileageLimit(int mileageLimit) {
        this.mileageLimit = mileageLimit;
    }

    public boolean isCollisionProtection() {
        return collisionProtection;
    }

    public void setCollisionProtection(boolean collisionProtection) {
        this.collisionProtection = collisionProtection;
    }

    public int getChildrenSeats() {
        return childrenSeats;
    }

    public void setChildrenSeats(int childrenSeats) {
        this.childrenSeats = childrenSeats;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public VehicleFuelTypeDTO getFuelType() {
        return fuelType;
    }

    public void setFuelType(VehicleFuelTypeDTO fuelType) {
        this.fuelType = fuelType;
    }

    public VehicleStyleDTO getStyle() {
        return style;
    }

    public void setStyle(VehicleStyleDTO style) {
        this.style = style;
    }

    public VehicleTransmissionDTO getTransmission() {
        return transmission;
    }

    public void setTransmission(VehicleTransmissionDTO transmission) {
        this.transmission = transmission;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "VehicleDetailsDTO{" +
                "id=" + id +
                ", mileage=" + mileage +
                ", mileageLimit=" + mileageLimit +
                ", collisionProtection=" + collisionProtection +
                ", childrenSeats=" + childrenSeats +
                ", picturePath='" + picturePath + '\'' +
                ", fuelType=" + fuelType +
                ", style=" + style +
                ", transmission=" + transmission +
                ", location=" + location +
                '}';
    }
}
