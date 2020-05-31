package com.example.vehicle.service;

import com.example.vehicle.dto.VehicleDetailsDTO;
import com.example.vehicle.dto.builders.VehicleDetailsDTOBuilder;
import com.example.vehicle.dto.VehicleMainViewDTO;
import com.example.vehicle.model.Notification;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public Long createVehicle(Vehicle vehicle) {
        try{
            Vehicle v = vehicleRepository.save(vehicle);
            return v.getId();
        }
        catch (Exception e){

        }
        return null;
    }

    public Notification updateVehicle(Vehicle vehicle) {
        Notification notification = new Notification("Updating vehicle failed");
        try{
            if (vehicleRepository.findById(vehicle.getId()).isPresent()){
                vehicleRepository.save(vehicle);
                notification.setText("Vehicle updated.");
            }
            else{
                notification.setText("Vehicle id does not exist.");
            }
        }
        catch (Exception e){

        }
        return notification;
    }

    public VehicleDetailsDTO getVehicleDetails(Long vehicleId) {
        return new VehicleDetailsDTOBuilder().createVehicleDetailsDTO();
    }

    public List<VehicleMainViewDTO> getAll() {
        return null;
    }

    public Notification delete(Long vehicleId) {
        Notification notification = new Notification("Deleting vehicle failed");
        try{
            if (vehicleRepository.findById(vehicleId).isPresent()){
                vehicleRepository.deleteById(vehicleId);
                notification.setText("Vehicle deleted.");
            }
            else{
                notification.setText("Vehicle id does not exist.");
            }
        }
        catch (Exception e){

        }
        return notification;
    }
}
