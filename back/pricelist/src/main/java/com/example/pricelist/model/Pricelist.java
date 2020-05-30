package com.example.pricelist.model;

import com.example.pricelist.model.builders.PricelistBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pricelist {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name="start_date", nullable=false)
    private LocalDate startDate;

    @Column(name="end_date", nullable = false)
    private LocalDate  endDate;

    @Column(name="price", nullable = false)
    private float price;

    @Column(name="price_by_mile", nullable = false)
    private float priceByMile;

    @Column(name="price_collision", nullable = false)
    private float priceCollision;

    @Column(name="vehicle_id", nullable = false)
    private Long vehicleId;

    @OneToOne
    @JoinColumn(name = "discount_id", referencedColumnName = "id", nullable = false)
    private VehicleDiscount vehicleDiscount;

    public Pricelist() {
    }

    public Pricelist(LocalDate startDate, LocalDate endDate, float price, float priceByMile, float priceCollision, Long vehicleId, VehicleDiscount vehicleDiscount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.priceByMile = priceByMile;
        this.priceCollision = priceCollision;
        this.vehicleId = vehicleId;
        this.vehicleDiscount = vehicleDiscount;
    }

    public static PricelistBuilder builder(){
        return new PricelistBuilder();
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

    public VehicleDiscount getVehicleDiscount() {
        return vehicleDiscount;
    }

    public void setVehicleDiscount(VehicleDiscount vehicleDiscount) {
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
