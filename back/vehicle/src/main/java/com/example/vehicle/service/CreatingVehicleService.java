package com.example.vehicle.service;

import com.example.vehicle.dto.NewVehicleDTO;
import com.example.vehicle.model.Notification;
import com.example.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatingVehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public Notification createVehicle(NewVehicleDTO newVehicleDTO) {

        return new Notification("Your vehicle could not be created.");
    }

}
