package com.example.vehicle.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VehicleSearchDTO {

    private Long make;
    private Long model;
    private Long style;
    private Long fuel;
    private Long transmission;

    private float priceLowerLimit;
    private float priceUpperLimit;
    private int currentMileage;
    private int mileageLimit;
    private boolean collisionProtection;
    private int childrenSeats;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String state;
    private String city;

    public VehicleSearchDTO() {
    }

    public VehicleSearchDTO(Long make, Long model, Long style, Long fuel, Long transmission, float priceLowerLimit, float priceUpperLimit, int currentMileage, int mileageLimit, boolean collisionProtection, int childrenSeats, LocalDateTime startDate, LocalDateTime endDate, String state, String city) {
        this.make = make;
        this.model = model;
        this.style = style;
        this.fuel = fuel;
        this.transmission = transmission;
        this.priceLowerLimit = priceLowerLimit;
        this.priceUpperLimit = priceUpperLimit;
        this.currentMileage = currentMileage;
        this.mileageLimit = mileageLimit;
        this.collisionProtection = collisionProtection;
        this.childrenSeats = childrenSeats;
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
        this.city = city;
    }

    public Long getMake() {
        return make;
    }

    public void setMake(Long make) {
        this.make = make;
    }

    public Long getModel() {
        return model;
    }

    public void setModel(Long model) {
        this.model = model;
    }

    public Long getStyle() {
        return style;
    }

    public void setStyle(Long style) {
        this.style = style;
    }

    public Long getFuel() {
        return fuel;
    }

    public void setFuel(Long fuel) {
        this.fuel = fuel;
    }

    public Long getTransmission() {
        return transmission;
    }

    public void setTransmission(Long transmission) {
        this.transmission = transmission;
    }

    public float getPriceLowerLimit() {
        return priceLowerLimit;
    }

    public void setPriceLowerLimit(float priceLowerLimit) {
        this.priceLowerLimit = priceLowerLimit;
    }

    public float getPriceUpperLimit() {
        return priceUpperLimit;
    }

    public void setPriceUpperLimit(float priceUpperLimit) {
        this.priceUpperLimit = priceUpperLimit;
    }

    public int getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(int currentMileage) {
        this.currentMileage = currentMileage;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "VehicleSearchDTO{" +
                "make=" + make +
                ", model=" + model +
                ", style=" + style +
                ", fuel=" + fuel +
                ", transmission=" + transmission +
                ", priceLowerLimit=" + priceLowerLimit +
                ", priceUpperLimit=" + priceUpperLimit +
                ", currentMileage=" + currentMileage +
                ", mileageLimit=" + mileageLimit +
                ", collisionProtection=" + collisionProtection +
                ", childrenSeats=" + childrenSeats +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
