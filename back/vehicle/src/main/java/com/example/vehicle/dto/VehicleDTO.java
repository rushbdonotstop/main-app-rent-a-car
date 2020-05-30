package com.example.vehicle.dto;
import com.example.vehicle.dto.catalogue.*;
import com.example.vehicle.dto.location.LocationDTO;
import com.example.vehicle.dto.pricelist.PriceListDTO;
import com.example.vehicle.dto.user.OwnerDTO;
import org.joda.time.LocalDate;

public class VehicleDTO {

    private long id;
    private int mileage;
    private int mileageLimit;
    private boolean collisionProtection;
    private int childrenSeats;
    private String picturePath;
    private LocalDate startDate;
    private LocalDate endDate;
    private VehicleFuelTypeDTO fuelType;
    private VehicleMakeDTO make;
    private VehicleModelDTO model;
    private VehicleStyleDTO style;
    private VehicleTransmissionDTO transmission;
    private LocationDTO location;
    private PriceListDTO priceListDTO;
    private OwnerDTO ownerDTO;

    public VehicleDTO() {
    }

    public VehicleDTO(long id, int mileage, int mileageLimit, boolean collisionProtection, int childrenSeats, String picturePath, LocalDate startDate, LocalDate endDate, VehicleFuelTypeDTO fuelType, VehicleMakeDTO make, VehicleModelDTO model, VehicleStyleDTO style, VehicleTransmissionDTO transmission, LocationDTO location, OwnerDTO ownerDTO) {
        this.id = id;
        this.mileage = mileage;
        this.mileageLimit = mileageLimit;
        this.collisionProtection = collisionProtection;
        this.childrenSeats = childrenSeats;
        this.picturePath = picturePath;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fuelType = fuelType;
        this.make = make;
        this.model = model;
        this.style = style;
        this.transmission = transmission;
        this.location = location;
        this.ownerDTO = ownerDTO;
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

    public boolean isCollisionProtection() {
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

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public VehicleFuelTypeDTO getFuelType() {
        return fuelType;
    }

    public void setFuelType(VehicleFuelTypeDTO fuelType) {
        this.fuelType = fuelType;
    }

    public VehicleMakeDTO getMake() {
        return make;
    }

    public void setMake(VehicleMakeDTO make) {
        this.make = make;
    }

    public VehicleModelDTO getModel() {
        return model;
    }

    public void setModel(VehicleModelDTO model) {
        this.model = model;
    }

    public VehicleStyleDTO getStyle() {
        return style;
    }

    public void setStyle(VehicleStyleDTO style) {
        this.style = style;
    }

    public VehicleTransmissionDTO getTransmission() {
        return transmission;
    }

    public void setTransmission(VehicleTransmissionDTO transmission) {
        this.transmission = transmission;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public PriceListDTO getPriceListDTO() {
        return priceListDTO;
    }

    public void setPriceListDTO(PriceListDTO priceListDTO) {
        this.priceListDTO = priceListDTO;
    }

    public OwnerDTO getOwnerDTO() {
        return ownerDTO;
    }

    public void setOwnerDTO(OwnerDTO ownerDTO) {
        this.ownerDTO = ownerDTO;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "id=" + id +
                ", mileage=" + mileage +
                ", mileageLimit=" + mileageLimit +
                ", collisionProtection=" + collisionProtection +
                ", childrenSeats=" + childrenSeats +
                ", picturePath='" + picturePath + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", fuelType=" + fuelType +
                ", make=" + make +
                ", model=" + model +
                ", style=" + style +
                ", transmission=" + transmission +
                ", location=" + location +
                ", priceListDTO=" + priceListDTO +
                ", ownerDTO=" + ownerDTO +
                '}';
    }
}
