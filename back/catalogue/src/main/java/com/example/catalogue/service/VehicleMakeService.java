package com.example.catalogue.service;

import com.example.catalogue.model.VehicleMake;
import com.example.catalogue.model.VehicleModel;
import com.example.catalogue.repository.VehicleMakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class VehicleMakeService {
    @Autowired
    private VehicleMakeRepository vehicleMakeRepository;

    public void addNewMake(VehicleMake vehicleMake) throws Exception {
        if(exist(vehicleMake)) {
            throw new Exception("Already exist");
        } else {
            vehicleMakeRepository.save(vehicleMake);
        }
    }

    public List<VehicleMake> getAllMakes() {
        return vehicleMakeRepository.findAll();
    }


    public boolean exist(VehicleMake vehicleMake) {
        List<VehicleMake> vehicleMakeList = vehicleMakeRepository.findAll();
        for (VehicleMake vm: vehicleMakeList
        ) {
            if(vm.getValue().toLowerCase().equals(vehicleMake.getValue().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public VehicleMake findOneMake(Long id) throws Exception{
        VehicleMake vehicleMake = null;
        try {
            vehicleMake = vehicleMakeRepository.findOneById(id);
        } catch (EntityNotFoundException e) {
            throw new Exception("Can't find vehicle make with id = " + id);
        }
        return vehicleMake;
    }

    public void deleteOneMake(Long id) throws Exception {
        try {
            findOneMake(id);
        } catch (EntityNotFoundException e) {
            throw new Exception("Can't find vehicle make with id = " + id);
        }
        vehicleMakeRepository.delete(findOneMake(id));
    }

    public void changeMake(Long id, VehicleMake vehicleMake1) throws Exception{
        try {
            VehicleMake vehicleMake = findOneMake(id);
            vehicleMake.setValue(vehicleMake1.getValue());
            vehicleMakeRepository.save(vehicleMake);
        } catch (EntityNotFoundException e) {
            throw new Exception("Can't find vehicle make with id = " + id);
        }
    }

    public VehicleMake createMake(VehicleMake vehicleMake) {
        if(exist(vehicleMake)) {
           return vehicleMakeRepository.findByValue(vehicleMake.getValue());
        } else {
           return vehicleMakeRepository.save(vehicleMake);
        }
    }
//    public VehicleMake findOneMakeByModel(String id) throws Exception{
//        VehicleMake vehicleMake = null;
//        try {
//            List<VehicleMake> vehicleMakeList = getAllMakes();
//            for (VehicleMake vm:
//                 vehicleMakeList) {
//                if(vm.getId() == )
//            }
//        } catch (EntityNotFoundException e) {
//            throw new Exception("Can't find vehicle make with model id = " + id);
//        }
//        return vehicleMake;
//    }
}
