package com.example.vehicle.dto;

import com.example.vehicle.dto.builders.VehicleDetailsDTOBuilder;

public class VehicleDetailsDTO {

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

    public VehicleDetailsDTO() {
    }

    public VehicleDetailsDTO(long id, int mileage, int mileageLimit, boolean collisionProtection, int childrenSeats, String picturePath, String fuelType, String style, String transmission, String location, String priceByMile, String priceCollision, String discount, String numDaysDiscount) {
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
        this.priceByMile = priceByMile;
        this.priceCollision = priceCollision;
        this.discount = discount;
        this.numDaysDiscount = numDaysDiscount;
    }

    public static VehicleDetailsDTOBuilder builder(){
        return new VehicleDetailsDTOBuilder();
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

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPriceByMile() {
        return priceByMile;
    }

    public void setPriceByMile(String priceByMile) {
        this.priceByMile = priceByMile;
    }

    public String getPriceCollision() {
        return priceCollision;
    }

    public void setPriceCollision(String priceCollision) {
        this.priceCollision = priceCollision;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getNumDaysDiscount() {
        return numDaysDiscount;
    }

    public void setNumDaysDiscount(String numDaysDiscount) {
        this.numDaysDiscount = numDaysDiscount;
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
                ", fuelType='" + fuelType + '\'' +
                ", style='" + style + '\'' +
                ", transmission='" + transmission + '\'' +
                ", location='" + location + '\'' +
                ", priceByMile='" + priceByMile + '\'' +
                ", priceCollision='" + priceCollision + '\'' +
                ", discount='" + discount + '\'' +
                ", numDaysDiscount='" + numDaysDiscount + '\'' +
                '}';
    }
}
