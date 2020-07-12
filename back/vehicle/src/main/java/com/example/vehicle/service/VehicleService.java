package com.example.vehicle.service;

import com.example.vehicle.model.Notification;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.model.VehicleImage;
import com.example.vehicle.repository.VehicleImageRepository;
import com.example.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    VehicleImageRepository imageRepository;

    public List<Vehicle> getAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle get(Long vehicleId) {
        Vehicle v = null;
        try{
            if (vehicleRepository.findById(vehicleId).isPresent()){
                v = vehicleRepository.findById(vehicleId).get();
            }
        }
        catch (Exception e){

        }
        return v;
    }

    public Vehicle create(Vehicle vehicle) {
            vehicle.setId(null);

            if(invalidDate(vehicle.getStartDate(), vehicle.getEndDate())){
                System.out.println("invalid");
                return null;
            }

            if(vehicle.getMileage() < 0 || vehicle.getMileageLimit() < 0 || vehicle.getChildrenSeats() < 0){
                System.out.println("invalid2");
                return null;
            }

            // povezi sliku
            if(vehicle.getImage() != null){
                vehicle.setImage(imageRepository.findByName(vehicle.getImage().getName()).get());
            }
            else{
                vehicle.setImage(null);
            }

            Vehicle v = vehicleRepository.save(vehicle);
            return v;

    }

    public Notification update(Vehicle vehicle) {
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

    public Boolean exists(Long vehicleId) {
        if (get(vehicleId) == null){
            return false;
        }
        return true;
    }

    public boolean invalidDate(LocalDateTime startDate, LocalDateTime endDate){
        if (startDate.toLocalDate().isAfter(endDate.toLocalDate()) || (startDate.toLocalDate().isBefore(LocalDateTime.now().toLocalDate()) && !startDate.toLocalDate().equals(LocalDateTime.now().toLocalDate()))){
            return true;
        }
        return false;
    }

    public Vehicle addAgentVehicle(Vehicle toModel) {
        Vehicle newVehicle = new Vehicle();
        try{
            VehicleImage vi = imageRepository.save(toModel.getImage());
            toModel.setImage(imageRepository.findByName(vi.getName()).get());
            newVehicle = vehicleRepository.save(toModel);
            vehicleRepository.save(newVehicle);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return newVehicle;
    }

    public Boolean canUserDelete(Long userId) {
        try {
            if(vehicleRepository.findAllByUserId(userId).size() != 0){
                return false;
            }
            else{
                return true;
            }

        } catch (Exception e) {
        }
        return false;
    }

    public List<Vehicle> getAllFromUser(Long userId) {
        return this.vehicleRepository.findAllByUserId(userId);
    }
}
