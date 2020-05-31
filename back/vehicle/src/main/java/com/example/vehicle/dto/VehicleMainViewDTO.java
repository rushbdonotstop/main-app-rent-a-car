package com.example.vehicle.dto;

public class VehicleMainViewDTO {

    private long id;
    private String make;
    private String model;
    private float price;
    private String ownerUsername;

    public VehicleMainViewDTO() {
    }

    public VehicleMainViewDTO(long id, String make, String model, float price, String ownerUsername) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.price = price;
        this.ownerUsername = ownerUsername;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
                ", price='" + price + '\'' +
                ", ownerUsername='" + ownerUsername + '\'' +
                '}';
    }
}
