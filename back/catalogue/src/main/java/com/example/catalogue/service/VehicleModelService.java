package com.example.catalogue.service;

import com.example.catalogue.model.VehicleMake;
import com.example.catalogue.model.VehicleModel;
import com.example.catalogue.repository.VehicleModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class VehicleModelService {
    @Autowired
    private VehicleModelRepository vehicleModelRepository;

    public void addNewModel(VehicleModel vehicleModel) throws Exception {
        if(exist(vehicleModel)) {
            throw new Exception("Already exist");
        } else {
            vehicleModelRepository.save(vehicleModel);
        }
    }

    public List<VehicleModel> getModelsByMake(VehicleMake vehicleMake) {return vehicleModelRepository.findByVehicleMake(vehicleMake);}

    public List<VehicleModel> getAllModels() {
        return vehicleModelRepository.findAll();
    }

    private boolean exist(VehicleModel vehicleModel) {
        List<VehicleModel> vehicleMakeList = vehicleModelRepository.findAll();
        for (VehicleModel vm: vehicleMakeList
        ) {
            if(vm.getValue().toLowerCase().equals(vehicleModel.getValue().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public VehicleModel findOneModel(Long id) throws Exception{
        VehicleModel vehicleModel = null;
        try {
            vehicleModel = vehicleModelRepository.findOneById(id);
        } catch (EntityNotFoundException e) {
            throw new Exception("Can't find vehicle model with id = " + id);
        }
        return vehicleModel;
    }

    public void deleteOneModel(Long id) throws Exception {
        try {
            findOneModel(id);
        } catch (EntityNotFoundException e) {
            throw new Exception("Can't find vehicle model with id = " + id);
        }
        vehicleModelRepository.delete(findOneModel(id));
    }

    public void changeModel(Long id, VehicleModel vehicleModel1) throws Exception{
        try {
            VehicleModel vehicleModel = findOneModel(id);
            vehicleModel.setValue(vehicleModel1.getValue());
            vehicleModelRepository.save(vehicleModel);
        } catch (EntityNotFoundException e) {
            throw new Exception("Can't find vehicle model with id = " + id);
        }
    }

    public VehicleModel createModel(VehicleModel vehicleModel) {
        if(exist(vehicleModel)) {
            return vehicleModelRepository.findByValue(vehicleModel.getValue());
        } else {
            return vehicleModelRepository.save(vehicleModel);
        }
    }
}
