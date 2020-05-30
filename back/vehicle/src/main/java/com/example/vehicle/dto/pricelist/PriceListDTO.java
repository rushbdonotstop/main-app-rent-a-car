package com.example.vehicle.dto.pricelist;

import com.example.vehicle.dto.builders.PriceListBuilderDTO;

import java.time.LocalDate;

public class PriceListDTO {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private float price;
    private float priceByMile;
    private float priceCollision;
    private Long vehicleId;
    private VehicleDiscountDTO vehicleDiscount;

    public PriceListDTO() {
    }

    public PriceListDTO(LocalDate startDate, LocalDate endDate, float price, float priceByMile, float priceCollision, Long vehicleId, VehicleDiscountDTO vehicleDiscount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.priceByMile = priceByMile;
        this.priceCollision = priceCollision;
        this.vehicleId = vehicleId;
        this.vehicleDiscount = vehicleDiscount;
    }

    public static PriceListBuilderDTO builder(){
        return new PriceListBuilderDTO();
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

    public VehicleDiscountDTO getVehicleDiscount() {
        return vehicleDiscount;
    }

    public void setVehicleDiscount(VehicleDiscountDTO vehicleDiscount) {
        this.vehicleDiscount = vehicleDiscount;
    }

    @Override
    public String toString() {
        return "PriceList{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", priceByMile=" + priceByMile +
                ", priceCollision=" + priceCollision +
                ", vehicleId=" + vehicleId +
                ", vehicleDiscount=" + vehicleDiscount +
                '}';
    }
}
