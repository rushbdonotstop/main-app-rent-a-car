package com.example.location.service;

import com.example.location.model.Notification;
import com.example.location.repository.StreetRepository;
import com.example.location.model.Street;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreetService {

    @Autowired
    private StreetRepository streetRepository;

    public List<Street> getAll() {
        List<Street> streets = null;
        try {
            streets = streetRepository.findAll();
        }
        catch (Exception e){

        }
        return streets;
    }

    public Street get(Long streetId) {
        Street street = null;
        try {
            if (streetRepository.findById(streetId).isPresent()){
                street = streetRepository.findById(streetId).get();
            }
        }
        catch (Exception e){

        }
        return street;
    }

    public Notification delete(Long streetId) {
        Notification notification = new Notification("Deleting street failed.");
        try {
            if (streetRepository.findById(streetId).isPresent()){
                streetRepository.deleteById(streetId);
                notification.setText("Deleted street.");
            }
            else{
                notification.setText("Street id does not exist.");
            }
        }
        catch (Exception e){

        }
        return notification;
    }

    public Notification create(Street street) {
        Notification notification = new Notification("Creating street failed.");
        try {
            if (streetRepository.findByValue(street.getValue()) == null){
                streetRepository.save(street);
                notification.setText("Created street.");
            }
            else{
                notification.setText("Street already exists.");
            }
        }
        catch (Exception e){

        }
        return notification;
    }
}
