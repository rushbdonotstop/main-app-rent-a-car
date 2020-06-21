package com.example.location.service;

import com.example.location.model.Notification;
import com.example.location.repository.CityRepository;
import com.example.location.repository.LocationRepository;
import com.example.location.repository.StateRepository;
import com.example.location.repository.StreetRepository;
import com.example.location.model.City;
import com.example.location.model.Location;
import com.example.location.model.State;
import com.example.location.model.Street;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.SortedSet;

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

    public Location get(Long locationId) {
        Location location = null;
        try{
            if(locationRepository.findById(locationId).isPresent()){
                location = locationRepository.findById(locationId).get();
            }
        }
        catch (Exception e){

        }
        return location;
    }

    public Notification delete(Long locationId) {
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

    public Location create(Location location) {
        Location locationNew = null;
//        try{
            if(locationRepository.findByStateAndCityAndStreet(location.getState().getValue(), location.getCity().getValue(), location.getStreet().getValue()) != null){
                locationNew = locationRepository.findByStateAndCityAndStreet(location.getState().getValue(), location.getCity().getValue(), location.getStreet().getValue());
            }
            else{
                State stateObject = stateRepository.findByValue(location.getState().getValue());
                if (stateObject == null){
                    stateObject = new State();
                    stateObject.setValue(location.getState().getValue());
                    stateObject = stateRepository.save(stateObject);
                }
                City cityObject = cityRepository.findByValue(location.getCity().getValue());
                if (cityObject == null){
                    cityObject = new City();
                    cityObject.setValue(location.getCity().getValue());
                    cityObject = cityRepository.save(cityObject);
                }
                Street streetObject = streetRepository.findByValue(location.getStreet().getValue());
                if (streetObject == null){
                    streetObject = new Street();
                    streetObject.setValue(location.getStreet().getValue());
                    streetObject = streetRepository.save(streetObject);
                }
                locationNew = new Location();
                locationNew.setCity(cityObject);
                locationNew.setState(stateObject);
                locationNew.setStreet(streetObject);
                locationNew = locationRepository.save(locationNew);
            }
//        }
//        catch (Exception e){
//
//        }
        return locationNew;
    }

    public Location addAgentLocation(Location locationNew) {
        Location l2 = new Location();
        try{
            State stateObject = stateRepository.save(locationNew.getState());
            City cityObject = cityRepository.save(locationNew.getCity());
            Street streetObject = streetRepository.save(locationNew.getStreet());
            l2.setCity(cityObject);
            l2.setState(stateObject);
            l2.setStreet(streetObject);
            l2 = locationRepository.save(locationNew);
        }
        catch (Exception e){

        }
        return l2;
    }

    public Long find(String state, String city, String street) {
        Long locationId = null;
        try{
            State stateObject = stateRepository.findByValue(state);
            City cityObject = cityRepository.findByValue(city);
            Street streetObject = streetRepository.findByValue(street);

            if (stateObject == null|| cityObject == null|| streetObject == null){
                locationId = null;
            }
            else{
                Location location = locationRepository.findByStateAndCityAndStreet(stateObject.getValue(), cityObject.getValue(), streetObject.getValue());
                if (location != null){
                    locationId = location.getId();
                }
            }
        }
        catch(Exception e){

        }
        return locationId;
    }

    public List<Location> getAll() {
        List<Location> locations = null;
        try{
            locations = locationRepository.findAll();
        }
        catch (Exception e){

        }

        return locations;
    }

    public SortedSet<City> getCitiesByState(Long stateId) {
        return this.locationRepository.getCitiesByState(stateId);
    }

    public void createAgentLocation(Location toModel) {
    }
}
