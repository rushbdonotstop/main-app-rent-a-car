package com.example.pricelist.model;

import java.util.Date;

public class PriceListBuilder {
    private Date startDate;
    private Date endDate;
    private float price;
    private float priceByMile;
    private float priceCollision;
    private Long vehicleId;

    public PriceListBuilder setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public PriceListBuilder setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }

    public PriceListBuilder setPrice(float price) {
        this.price = price;
        return this;
    }

    public PriceListBuilder setPriceByMile(float priceByMile) {
        this.priceByMile = priceByMile;
        return this;
    }

    public PriceListBuilder setPriceCollision(float priceCollision) {
        this.priceCollision = priceCollision;
        return this;
    }

    public PriceListBuilder setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public PriceList createPriceList() {
        return new PriceList(startDate, endDate, price, priceByMile, priceCollision, vehicleId);
    }
}
