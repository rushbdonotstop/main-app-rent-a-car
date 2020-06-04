package com.example.vehicle.dto.request;


import java.time.LocalDateTime;

public class RequestForVehicleDTO {

    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Status status;

    private Long vehicleId;


    public RequestForVehicleDTO() {
    }

    public RequestForVehicleDTO(Long id, LocalDateTime startDate, LocalDateTime endDate, Status status, Long vehicleId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.vehicleId = vehicleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    @Override
    public String toString() {
        return "RequestForVehicleDTO{" +
                "id=" + id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", vehicleId=" + vehicleId +
                '}';
    }
}
