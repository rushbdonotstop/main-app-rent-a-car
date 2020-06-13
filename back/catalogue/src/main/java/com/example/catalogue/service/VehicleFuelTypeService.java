package com.example.catalogue.service;

import com.example.catalogue.model.VehicleFuelType;
import com.example.catalogue.repository.VehicleFuelTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class VehicleFuelTypeService {
    @Autowired
    private VehicleFuelTypeRepository vehicleFuelTypeRepository;

    public void addNewFuelType(VehicleFuelType vehicleFuelType) throws Exception {
        if(exist(vehicleFuelType)) {
            throw new Exception("Already exist");
        } else {
            vehicleFuelTypeRepository.save(vehicleFuelType);
        }
    }

    public List<VehicleFuelType> getAllFuelType() {
        return vehicleFuelTypeRepository.findAll();
    }


    public boolean exist(VehicleFuelType vehicleFuelType) {
        List<VehicleFuelType> vehicleFuelTypeList = vehicleFuelTypeRepository.findAll();
        for (VehicleFuelType vft: vehicleFuelTypeList
             ) {
            if(vft.getValue().toLowerCase().equals(vehicleFuelType.getValue().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public VehicleFuelType findOneFuelType(String id) throws Exception{
        VehicleFuelType vehicleFuelType = null;
        try {
            vehicleFuelType = vehicleFuelTypeRepository.findOneById(Long.parseLong(id));
        } catch (EntityNotFoundException e) {
            throw new Exception("Can't find fuel type with id = " + id);
        }
        return vehicleFuelType;
    }

    public void deleteOneFuelType(String id) throws Exception {
        try {
            findOneFuelType(id);
        } catch (EntityNotFoundException e) {
            throw new Exception("Can't find fuel type with id = " + id);
        }
        vehicleFuelTypeRepository.delete(findOneFuelType(id));
    }

    public void changeFuelType(String id, VehicleFuelType vehicleFuelType1) throws Exception{
        try {
            VehicleFuelType vehicleFuelType = findOneFuelType(id);
            vehicleFuelType.setValue(vehicleFuelType1.getValue());
            vehicleFuelTypeRepository.save(vehicleFuelType);
        } catch (EntityNotFoundException e) {
            throw new Exception("Can't find fuel type with id = " + id);
        }
    }
}
