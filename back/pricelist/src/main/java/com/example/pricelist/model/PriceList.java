package com.example.pricelist.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PriceList {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name="start_date", nullable=false)
    private Date startDate;

    @Column(name="end_date", nullable = false)
    private Date endDate;

    @Column(name="price", nullable = false)
    private float price;

    @Column(name="price_by_mile", nullable = false)
    private float priceByMile;

    @Column(name="price_collision", nullable = false)
    private float priceCollision;

    @Column(name="vehicle_id", nullable = false)
    private Long vehicleId;

    public PriceList() {
    }

    public PriceList(Date startDate, Date endDate, float price, float priceByMile, float priceCollision, Long vehicleId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.priceByMile = priceByMile;
        this.priceCollision = priceCollision;
        this.vehicleId = vehicleId;
    }

    public static PriceListBuilder builder(){
        return new PriceListBuilder();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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

    @Override
    public String toString() {
        return "PriceList{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", price=" + price +
                ", priceByMile=" + priceByMile +
                ", priceCollision=" + priceCollision +
                '}';
    }
}
