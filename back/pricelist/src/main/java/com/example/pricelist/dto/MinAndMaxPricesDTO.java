package com.example.pricelist.dto;

public class MinAndMaxPricesDTO {

    private float minPrice;
    private float maxPrice;

    public MinAndMaxPricesDTO(float minPrice, float maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public MinAndMaxPricesDTO() {
    }

    public float getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return "MaxAndMinPricesDTO{" +
                "minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
