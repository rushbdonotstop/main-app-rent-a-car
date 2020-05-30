package com.example.vehicle.service;

import com.example.vehicle.dto.VehicleDTO;
import com.example.vehicle.dto.VehicleDetailsDTO;
import com.example.vehicle.dto.VehicleMainViewDTO;
import com.example.vehicle.model.Notification;
import com.example.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public Notification createVehicle(VehicleDTO vehicleDTO) {

        return new Notification("Your vehicle could not be created.");
    }

    public VehicleDetailsDTO getVehicleDetails(Long vehicleId) {
        return new VehicleDetailsDTO();
    }

    public List<VehicleMainViewDTO> getAll() {
        return null;
    }
}
