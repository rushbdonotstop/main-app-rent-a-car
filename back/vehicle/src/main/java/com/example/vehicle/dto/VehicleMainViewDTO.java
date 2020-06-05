package com.example.vehicle.dto;

import java.time.LocalDateTime;

public class VehicleMainViewDTO {

    private Long id;
    private String make;
    private String model;
    private float price;
    private String ownerUsername;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long ownerId;

    public VehicleMainViewDTO() {
    }

    public VehicleMainViewDTO(Long id, String make, String model, float price, String ownerUsername) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.price = price;
        this.ownerUsername = ownerUsername;
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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public void setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
    }

    @Override
    public String toString() {
        return "VehicleMainViewDTO{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", ownerUsername='" + ownerUsername + '\'' +
                '}';
    }
}
