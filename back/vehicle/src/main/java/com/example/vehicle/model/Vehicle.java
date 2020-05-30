package com.example.vehicle.model;

import com.example.vehicle.model.builder.VehicleBuilder;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;

    @Column(name="mileage", nullable = false)
    private int mileage;

    @Column(name="milleage_limit", nullable = false)
    private int mileageLimit;

    @Column(name="collision_protection", nullable = false)
    private boolean collisionProtection;

    @Column(name="children_seats", nullable = false)
    private int childrenSeats;

    @Column(name="picture", nullable = false)
    private int picture;

    @Column(name="start_date", nullable = false)
    private Date startDate;

    @Column(name="end_date", nullable = false)
    private Date endDate;

    @Column(name="vehicle_fuel_type_id", nullable = false)
    private Long vehicleFuelTypeId;

    @Column(name="vehicle_make_id", nullable = false)
    private Long vehicleMakeId;

    @Column(name="vehicle_model_id", nullable = false)
    private Long vehicleModelId;

    @Column(name="vehicle_style_id", nullable = false)
    private Long vehicleStypeId;

    @Column(name="vehicle_transmission_id", nullable = false)
    private Long vehicleTransmissionId;

    public Vehicle(long id, int mileage, int mileageLimit, boolean collisionProtection, int childrenSeats, int picture, Date startDate, Date endDate, Long vehicleFuelTypeId, Long vehicleMakeId, Long vehicleModelId, Long vehicleStypeId, Long vehicleTransmissionId) {
        this.id = id;
        this.mileage = mileage;
        this.mileageLimit = mileageLimit;
        this.collisionProtection = collisionProtection;
        this.childrenSeats = childrenSeats;
        this.picture = picture;
        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicleFuelTypeId = vehicleFuelTypeId;
        this.vehicleMakeId = vehicleMakeId;
        this.vehicleModelId = vehicleModelId;
        this.vehicleStypeId = vehicleStypeId;
        this.vehicleTransmissionId = vehicleTransmissionId;
    }

    public Vehicle() {
    }

    public static VehicleBuilder builder(){
        return new VehicleBuilder();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getMileageLimit() {
        return mileageLimit;
    }

    public void setMileageLimit(int mileageLimit) {
        this.mileageLimit = mileageLimit;
    }

    public boolean getCollisetionProtection() {
        return collisionProtection;
    }

    public void setCollisionProtection(boolean collisionProtection) {
        this.collisionProtection = collisionProtection;
    }

    public int getChildrenSeats() {
        return childrenSeats;
    }

    public void setChildrenSeats(int childrenSeats) {
        this.childrenSeats = childrenSeats;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
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

    public boolean isCollisionProtection() {
        return collisionProtection;
    }

    public Long getVehicleFuelTypeId() {
        return vehicleFuelTypeId;
    }

    public void setVehicleFuelTypeId(Long vehicleFuelTypeId) {
        this.vehicleFuelTypeId = vehicleFuelTypeId;
    }

    public Long getVehicleMakeId() {
        return vehicleMakeId;
    }

    public void setVehicleMakeId(Long vehicleMakeId) {
        this.vehicleMakeId = vehicleMakeId;
    }

    public Long getVehicleModelId() {
        return vehicleModelId;
    }

    public void setVehicleModelId(Long vehicleModelId) {
        this.vehicleModelId = vehicleModelId;
    }

    public Long getVehicleStypeId() {
        return vehicleStypeId;
    }

    public void setVehicleStypeId(Long vehicleStypeId) {
        this.vehicleStypeId = vehicleStypeId;
    }

    public Long getVehicleTransmissionId() {
        return vehicleTransmissionId;
    }

    public void setVehicleTransmission(Long vehicleTransmissionId) {
        this.vehicleTransmissionId = vehicleTransmissionId;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", mileage=" + mileage +
                ", mileageLimit=" + mileageLimit +
                ", collisionProtection=" + collisionProtection +
                ", childrenSeats=" + childrenSeats +
                ", picture=" + picture +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", vehicleFuelTypeId=" + vehicleFuelTypeId +
                ", vehicleMakeId=" + vehicleMakeId +
                ", vehicleModelId=" + vehicleModelId +
                ", vehicleStypeId=" + vehicleStypeId +
                ", vehicleTransmissionId=" + vehicleTransmissionId +
                '}';
    }
}
