package com.example.vehicle.dto;


import javax.persistence.*;
import java.time.LocalDate;

public class Pricelist implements Comparable<Pricelist>{

    private Long id;

    private LocalDate startDate;

    private LocalDate  endDate;

    private float price;

    private float priceByMile;

    private float priceCollision;

    private Long vehicleId;

    private VehicleDiscount vehicleDiscount;

    public Pricelist() {
    }

    public Pricelist(Long id, LocalDate startDate, LocalDate endDate, float price, float priceByMile, float priceCollision, Long vehicleId, VehicleDiscount vehicleDiscount) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.priceByMile = priceByMile;
        this.priceCollision = priceCollision;
        this.vehicleId = vehicleId;
        this.vehicleDiscount = vehicleDiscount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getPriceByMile() {
        return priceByMile;
    }

    public void setPriceByMile(float priceByMile) {
        this.priceByMile = priceByMile;
    }

    public float getPriceCollision() {
        return priceCollision;
    }

    public void setPriceCollision(float priceCollision) {
        this.priceCollision = priceCollision;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleDiscount getVehicleDiscountId() {
        return vehicleDiscount;
    }

    public void setVehicleDiscountId(VehicleDiscount vehicleDiscountId) {
        this.vehicleDiscount = vehicleDiscountId;
    }

    @Override
    public String toString() {
        return "PricelistDTO{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", priceByMile=" + priceByMile +
                ", priceCollision=" + priceCollision +
                ", vehicleId=" + vehicleId +
                ", vehicleDiscountId=" + vehicleDiscount +
                '}';
    }

    @Override
    public int compareTo(Pricelist o) {
        return this.startDate.compareTo(o.startDate);
    }
}
