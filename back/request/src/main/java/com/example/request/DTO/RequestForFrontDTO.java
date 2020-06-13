package com.example.request.DTO;

import com.example.request.model.enums.Status;

import java.time.LocalDateTime;

public class RequestForFrontDTO {

    private Long id;
    private Float totalCost;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status status;
    private String username;
    private String makePlusModel;
    private Long bundleId;

    public RequestForFrontDTO() {
    }

    public RequestForFrontDTO(Long id, Float totalCost, LocalDateTime startDate, LocalDateTime endDate, Status status, String username, String makePlusModel, Long bundleId) {
        this.id = id;
        this.totalCost = totalCost;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.username = username;
        this.makePlusModel = makePlusModel;
        this.bundleId = bundleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Float totalCost) {
        this.totalCost = totalCost;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMakePlusModel() {
        return makePlusModel;
    }

    public void setMakePlusModel(String makePlusModel) {
        this.makePlusModel = makePlusModel;
    }

    public Long getBundleId() {
        return bundleId;
    }

    public void setBundleId(Long bundleId) {
        this.bundleId = bundleId;
    }

    @Override
    public String toString() {
        return "RequestForFrontDTO{" +
                "id=" + id +
                ", totalCost=" + totalCost +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", makePlusModel='" + makePlusModel + '\'' +
                ", bundleId=" + bundleId +
                '}';
    }
}
