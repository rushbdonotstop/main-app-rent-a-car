package com.example.vehicle.dto.statistics;

import com.example.vehicle.model.Vehicle;

public class VehicleWithRating {

    private Vehicle vehicle;
    private float rating;
    private int numberOfReviews;

    public VehicleWithRating() {
    }

    public VehicleWithRating(Vehicle vehicle, float rating, int numberOfReviews) {
        this.vehicle = vehicle;
        this.rating = rating;
        this.numberOfReviews = numberOfReviews;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    @Override
    public String toString() {
        return "VehicleWithRating{" +
                "vehicle=" + vehicle +
                ", rating=" + rating +
                ", numberOfReviews=" + numberOfReviews +
                '}';
    }
}
