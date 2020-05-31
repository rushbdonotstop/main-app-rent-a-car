package com.example.location.service;

import com.example.location.model.*;
import com.example.location.repository.CityRepository;
import com.example.location.repository.LocationRepository;
import com.example.location.repository.StateRepository;
import com.example.location.repository.StreetRepository;
import com.sun.source.doctree.SeeTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StreetRepository streetRepository;

    public Location getVehicleLocation(Long locationId) {
        Location location = null;
        try{
            if(locationRepository.findById(locationId).isPresent()){
                location = locationRepository.findById(locationId).get();
            }
            return null;
        }
        catch (Exception e){

        }
        return location;
    }

    public Notification deleteLocation(Long locationId) {
        Notification notification = new Notification("Deleting location failed.");
        try{
            if(locationRepository.findById(locationId).isPresent()){
                locationRepository.deleteById(locationId);
                notification.setText("Deleted location.");
            }
            else{
                notification.setText("Location id does not exist.");
            }
        }
        catch (Exception e){

        }
        return notification;
    }

    public Notification createLocation(Location location) {
        Notification notification = new Notification("Creating location failed.");
        try{
            if(locationRepository.findByStateAndCityAndStreet(location.getState(), location.getCity(), location.getStreet()) != null){
                notification.setText("Location already exists.");
            }
            else{
                locationRepository.save(location);
                notification.setText("Created location.");
            }
        }
        catch (Exception e){

        }
        return notification;
    }

    public Long findLocation(String state, String city, String street) {
        try{
            State stateObject = stateRepository.findByState(state);
            if (stateObject == null){
                stateObject = stateRepository.save(new State(state));
            }
            City cityObject = cityRepository.findByCity(city);
            if (cityObject == null){
                cityObject = cityRepository.save(new City(city));
            }
            Street streetObject = streetRepository.findByStreet(street);
            if (streetObject == null){
                streetObject = streetRepository.save(new Street(street));
            }
            Location location = locationRepository.findByStateAndCityAndStreet(stateObject, cityObject, streetObject);
            if (location == null){
                location = locationRepository.save(new Location(stateObject, cityObject, streetObject));
            }
            return location.getId();
        }
        catch(Exception e){

        }
        return null;
    }
}
