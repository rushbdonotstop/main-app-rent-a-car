package com.example.location.service;

import com.example.location.model.Notification;
import com.example.location.repository.CityRepository;
import com.example.location.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public List<City> getAll() {
        List<City> cities = null;
        try {
            cities = cityRepository.findAll();
        }
        catch (Exception e){

        }
        return cities;
    }

    public City get(Long cityId) {
        City city = null;
        try {
            if (cityRepository.findById(cityId).isPresent()){
                city = cityRepository.findById(cityId).get();
            }
        }
        catch (Exception e){

        }
        return city;
    }

    public Notification delete(Long cityId) {
        Notification notification = new Notification("Delete city failed.");
        try {
            if (cityRepository.findById(cityId).isPresent()){
                cityRepository.deleteById(cityId);
                notification.setText("Deleted city.");
            }
            else{
                notification.setText("City id does not exist.");
            }
        }
        catch (Exception e){

        }
        return notification;
    }

    public Notification create(City city) {
        Notification notification = new Notification("Create city failed.");
        try {
            if (cityRepository.findByValue(city.getValue()) == null){
                cityRepository.save(city);
                notification.setText("Created city.");
            }
            else{
                notification.setText("City already exists.");
            }
        }
        catch (Exception e){

        }
        return notification;
    }
}
