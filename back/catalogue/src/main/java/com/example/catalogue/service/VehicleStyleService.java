package com.example.catalogue.service;

import com.example.catalogue.model.VehicleStyle;
import com.example.catalogue.repository.VehicleStyleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class VehicleStyleService {
    @Autowired
    private VehicleStyleRepository vehicleStyleRepository;

    public void addNewStyle(VehicleStyle vehicleStyle) throws Exception {
        if(exist(vehicleStyle)) {
            throw new Exception("Already exist");
        } else {
            vehicleStyleRepository.save(vehicleStyle);
        }
    }

    public List<VehicleStyle> getAllStyles() {
        return vehicleStyleRepository.findAll();
    }

    //TODO itekako moguca optimizacija
    private boolean exist(VehicleStyle vehicleStyle) {
        List<VehicleStyle> vehicleStyleList = vehicleStyleRepository.findAll();
        for (VehicleStyle vs: vehicleStyleList
        ) {
            if(vs.getValue().toLowerCase().equals(vehicleStyle.getValue().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public VehicleStyle findOneStyle(Long id) throws Exception{
        VehicleStyle vehicleStyle = null;
        try {
            vehicleStyle = vehicleStyleRepository.findOneById(id);
        } catch (EntityNotFoundException e) {
            throw new Exception("Can't find vehicle style with id = " + id);
        }
        return vehicleStyle;
    }

    public void deleteOneStyle(Long id) throws Exception {
        try {
            findOneStyle(id);
        } catch (EntityNotFoundException e) {
            throw new Exception("Can't find vehicle style with id = " + id);
        }
        vehicleStyleRepository.delete(findOneStyle(id));
    }

    public void changeStyle(Long id, VehicleStyle vehicleStyle1) throws Exception{
        try {
            VehicleStyle vehicleStyle = findOneStyle(id);
            vehicleStyle.setValue(vehicleStyle1.getValue());
            vehicleStyleRepository.save(vehicleStyle1);
        } catch (EntityNotFoundException e) {
            throw new Exception("Can't find vehicle style with id = " + id);
        }
    }

    public VehicleStyle createStyle(VehicleStyle vehicleStyle) {
        if(exist(vehicleStyle)) {
            return vehicleStyleRepository.findByValue(vehicleStyle.getValue());
        } else {
            return  vehicleStyleRepository.save(vehicleStyle);
        }
    }
}
