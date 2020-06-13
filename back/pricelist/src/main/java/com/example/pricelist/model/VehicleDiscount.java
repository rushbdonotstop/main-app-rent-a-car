package com.example.pricelist.model;

import javax.persistence.*;

@Entity
public class VehicleDiscount {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name="num_days", nullable = false)
    private int numDays;

    @Column(name="discount", nullable = false)
    private int discount;

    public VehicleDiscount(Long id, int numDays, int discount) {
        this.id = id;
        this.numDays = numDays;
        this.discount = discount;
    }

    public VehicleDiscount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumDays() {
        return numDays;
    }

    public void setNumDays(int numDays) {
        this.numDays = numDays;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "VehicleDiscount{" +
                "id=" + id +
                ", numDays=" + numDays +
                ", discount=" + discount +
                '}';
    }
}
