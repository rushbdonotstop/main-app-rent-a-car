package com.example.message.dto;


import java.time.LocalDateTime;

public class RequestDTO {

    private Long id;
    private Float totalCost;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Status status;
    private Long userId;
    private Long vehicleId;
    private Long ownerId;
    private Bundle bundle;

    public RequestDTO() {
    }

    public RequestDTO(Long id, Float totalCost, LocalDateTime startDate, LocalDateTime endDate, Status status, Long userId, Long vehicleId, Long ownerId, Bundle bundle) {
        this.id = id;
        this.totalCost = totalCost;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.ownerId = ownerId;
        this.bundle = bundle;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "id=" + id +
                ", totalCost=" + totalCost +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", userId=" + userId +
                ", vehicleId=" + vehicleId +
                ", ownerId=" + ownerId +
                ", bundle=" + bundle +
                '}';
    }
}
