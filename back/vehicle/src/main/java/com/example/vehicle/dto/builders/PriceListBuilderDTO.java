package com.example.vehicle.dto.builders;
import com.example.vehicle.dto.pricelist.PriceListDTO;
import com.example.vehicle.dto.pricelist.VehicleDiscountDTO;

import java.time.LocalDate;

public class PriceListBuilderDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    private float price;
    private float priceByMile;
    private float priceCollision;
    private Long vehicleId;
    private VehicleDiscountDTO vehicleDiscount;

    public PriceListBuilderDTO setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public PriceListBuilderDTO setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public PriceListBuilderDTO setPrice(float price) {
        this.price = price;
        return this;
    }

    public PriceListBuilderDTO setPriceByMile(float priceByMile) {
        this.priceByMile = priceByMile;
        return this;
    }

    public PriceListBuilderDTO setPriceCollision(float priceCollision) {
        this.priceCollision = priceCollision;
        return this;
    }

    public PriceListBuilderDTO setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
        return this;
    }

    public PriceListBuilderDTO setVehicleDiscount(VehicleDiscountDTO vehicleDiscount) {
        this.vehicleDiscount = vehicleDiscount;
        return this;
    }

    public PriceListDTO createPriceList() {
        return new PriceListDTO(startDate, endDate, price, priceByMile, priceCollision, vehicleId, vehicleDiscount);
    }
}
