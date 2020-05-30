package com.example.vehicle.dto;

import com.example.vehicle.dto.catalogue.VehicleMakeDTO;
import com.example.vehicle.dto.catalogue.VehicleModelDTO;
import com.example.vehicle.dto.pricelist.PriceListDTO;
import com.example.vehicle.dto.user.OwnerDTO;

public class VehicleMainViewDTO {

    private long id;
    private VehicleMakeDTO make;
    private VehicleModelDTO model;
    private PriceListDTO priceListDTO;
    private OwnerDTO ownerDTO;

    public VehicleMainViewDTO() {
    }

    public VehicleMainViewDTO(long id, VehicleMakeDTO make, VehicleModelDTO model, PriceListDTO priceListDTO, OwnerDTO ownerDTO) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.priceListDTO = priceListDTO;
        this.ownerDTO = ownerDTO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        return "VehicleMainViewDTO{" +
                "id=" + id +
                ", make=" + make +
                ", model=" + model +
                ", priceListDTO=" + priceListDTO +
                ", ownerDTO=" + ownerDTO +
                '}';
    }
}
