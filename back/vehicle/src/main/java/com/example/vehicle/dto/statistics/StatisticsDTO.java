package com.example.vehicle.dto.statistics;

public class StatisticsDTO {

    private String makeModelForMileage;
    private int mostMileage;
    private String makeModelForMostReviews;
    private int mostReviews;
    private String makeModelForBestRating;
    private float bestRating;

    public StatisticsDTO() {
    }

    public StatisticsDTO(String makeModelForMileage, int mostMileage, String makeModelForMostReviews, int mostReviews, String makeModelForBestRating, float bestRating) {
        this.makeModelForMileage = makeModelForMileage;
        this.mostMileage = mostMileage;
        this.makeModelForMostReviews = makeModelForMostReviews;
        this.mostReviews = mostReviews;
        this.makeModelForBestRating = makeModelForBestRating;
        this.bestRating = bestRating;
    }

    public String getMakeModelForMileage() {
        return makeModelForMileage;
    }

    public void setMakeModelForMileage(String makeModelForMileage) {
        this.makeModelForMileage = makeModelForMileage;
    }

    public int getMostMileage() {
        return mostMileage;
    }

    public void setMostMileage(int mostMileage) {
        this.mostMileage = mostMileage;
    }

    public String getMakeModelForMostReviews() {
        return makeModelForMostReviews;
    }

    public void setMakeModelForMostReviews(String makeModelForMostReviews) {
        this.makeModelForMostReviews = makeModelForMostReviews;
    }

    public int getMostReviews() {
        return mostReviews;
    }

    public void setMostReviews(int mostReviews) {
        this.mostReviews = mostReviews;
    }

    public String getMakeModelForBestRating() {
        return makeModelForBestRating;
    }

    public void setMakeModelForBestRating(String makeModelForBestRating) {
        this.makeModelForBestRating = makeModelForBestRating;
    }

    public float getBestRating() {
        return bestRating;
    }

    public void setBestRating(float bestRating) {
        this.bestRating = bestRating;
    }
}
