package com.example.catalogue.service;

import com.example.catalogue.model.*;
import com.example.catalogue.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class CatalogueService {

    @Autowired
    private VehicleFuelTypeRepository vehicleFuelTypeRepository;

    @Autowired
    private VehicleMakeRepository vehicleMakeRepository;

    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    @Autowired
    private VehicleTransmissionRepository vehicleTransmissionRepository;

    @Autowired
    private VehicleStyleRepository vehicleStyleRepository;



    public VehicleMake getVehicleMake(Long makeId) {
        try{
            if (vehicleMakeRepository.findById(makeId).isPresent()){
                return vehicleMakeRepository.findById(makeId).get();
            }
            return null;
        }
        catch(Exception e){

        }
        return null;
    }

    public VehicleModel getVehicleModel(Long modelId) {
        try{
            if (vehicleModelRepository.findById(modelId).isPresent()){
                return vehicleModelRepository.findById(modelId).get();
            }
            return null;
        }
        catch(Exception e){

        }
        return null;
    }

    public VehicleStyle getVehicleStyle(Long styleId) {
        try{
            if (vehicleStyleRepository.findById(styleId).isPresent()){
                return vehicleStyleRepository.findById(styleId).get();
            }
            return null;
        }
        catch(Exception e){

        }
        return null;
    }

    public VehicleTransmission getVehicleTransmission(Long transmissionId) {
        try{
            if (vehicleTransmissionRepository.findById(transmissionId).isPresent()){
                return vehicleTransmissionRepository.findById(transmissionId).get();
            }
            return null;
        }
        catch(Exception e){

        }
        return null;
    }

    public VehicleFuelType getVehicleFuelType(Long fuelTypeId) {
        try{
            if (vehicleFuelTypeRepository.findById(fuelTypeId).isPresent()){
                return vehicleFuelTypeRepository.findById(fuelTypeId).get();
            }
            return null;
        }
        catch(Exception e){

        }
        return null;
    }

    public List<VehicleMake> getVehicleMakeAll() {
        List<VehicleMake> makeList = null;
        try{
            makeList = vehicleMakeRepository.findAll();
        }
        catch (Exception e){

        }
        return makeList;
    }

    public List<VehicleModel> getVehicleModelAll() {
        List<VehicleModel> modelList = null;
        try{
            modelList = vehicleModelRepository.findAll();
        }
        catch (Exception e){

        }
        return modelList;
    }

    public List<VehicleStyle> getVehicleStyleAll() {
        List<VehicleStyle> styleList = null;
        try{
            styleList = vehicleStyleRepository.findAll();
        }
        catch (Exception e){

        }
        return styleList;
    }

    public List<VehicleTransmission> getVehicleTransmissionAll() {
        List<VehicleTransmission> transmissionList = null;
        try{
            transmissionList =  vehicleTransmissionRepository.findAll();
        }
        catch (Exception e){

        }
        return transmissionList;
    }

    public List<VehicleFuelType> getVehicleFuelTypeAll() {
        List<VehicleFuelType> fuelTypeList = null;
        try{
            fuelTypeList = vehicleFuelTypeRepository.findAll();
        }
        catch (Exception e){

        }
        return fuelTypeList;
    }
}
