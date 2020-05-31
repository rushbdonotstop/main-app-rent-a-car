package com.example.vehicle.dto;


import javax.persistence.*;
import java.time.LocalDate;

public class PricelistDTO {

    private Long id;

    private LocalDate startDate;

    private LocalDate  endDate;
    private float price;

    private float priceByMile;

    private float priceCollision;

    private Long vehicleId;

    private Long vehicleDiscountId;

    public PricelistDTO() {
    }

    public PricelistDTO(Long id, LocalDate startDate, LocalDate endDate, float price, float priceByMile, float priceCollision, Long vehicleId, Long vehicleDiscountId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.priceByMile = priceByMile;
        this.priceCollision = priceCollision;
        this.vehicleId = vehicleId;
        this.vehicleDiscountId = vehicleDiscountId;
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

    public Long getVehicleDiscountId() {
        return vehicleDiscountId;
    }

    public void setVehicleDiscountId(Long vehicleDiscountId) {
        this.vehicleDiscountId = vehicleDiscountId;
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
                ", vehicleDiscountId=" + vehicleDiscountId +
                '}';
    }
}
