package com.example.vehicle.service;

import com.example.vehicle.model.Notification;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

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

    public Long create(Vehicle vehicle) {
        try{
            vehicle.setId(null);

            if(invalidDate(vehicle.getStartDate(), vehicle.getEndDate())){
                return null;
            }

            if(vehicle.getMileage() < 0 || vehicle.getMileageLimit() < 0 || vehicle.getChildrenSeats() < 0){
                return null;
            }

            if(!Files.exists(Paths.get(vehicle.getPicturePath()))){
                vehicle.setPicturePath(null);
            }
            else{
                try {
                    Random random = new Random();
                    Long pictureName = random.nextLong();
                    while (vehicleRepository.findByPicturePath("./images/" + Math.abs(pictureName)) != null) {
                        pictureName = random.nextLong();
                    }
                    BufferedImage img = null;
                    img = ImageIO.read(new File(vehicle.getPicturePath()));
                    File outputFile = new File("./images/" + Math.abs(pictureName) + ".jpg");
                    ImageIO.write(img, "jpg", outputFile);

                    vehicle.setPicturePath("./images/" + Math.abs(pictureName) + ".jpg");
                }
                catch(Exception e){
                    vehicle.setPicturePath(null);
                }
            }

            Vehicle v = vehicleRepository.save(vehicle);
            return v.getId();
        }
        catch (Exception e){

        }
        return null;
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
        if (startDate.isAfter(endDate) || startDate.isBefore(LocalDateTime.now())){
            return true;
        }
        return false;
    }
}
