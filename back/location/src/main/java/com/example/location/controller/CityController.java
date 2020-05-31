package com.example.location.controller;

import com.example.location.model.City;
import com.example.location.model.Location;
import com.example.location.model.Notification;
import com.example.location.service.CityService;
import com.example.location.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("location/city")
public class CityController {

    @Autowired
    private CityService cityService;

    /**
     * GET /server/location/city
     *
     * @return return all cities
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<City>> getAll() throws Exception {
        List<City> cities = cityService.getAll();
        return new ResponseEntity<List<City>>(cities, HttpStatus.OK);
    }

    /**
     * GET /server/location/city/{id}
     *
     * @return return city with specific id
     */
    @GetMapping(value = "/{cityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<City> get(@PathVariable Long cityId) throws Exception {
        City city = cityService.get(cityId);
        return new ResponseEntity<City>(city, HttpStatus.OK);
    }

    /**
     * DELETE /server/location/city/{id}
     *
     * @return return status of delete city action
     */
    @DeleteMapping(value = "/{cityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> delete(@PathVariable Long cityId) throws Exception {
        Notification notification = cityService.delete(cityId);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    /**
     * CREATE /server/location/city
     *
     * @return return status of create city
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> create(@RequestBody City city) throws Exception {
        Notification notification = cityService.create(city);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }
}
