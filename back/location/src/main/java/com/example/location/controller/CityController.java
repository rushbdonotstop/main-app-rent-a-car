package com.example.location.controller;

import com.example.location.model.Notification;
import com.example.location.service.CityService;
import com.example.location.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("location/city")
public class CityController {

    @Autowired
    private CityService cityService;

    Logger logger = LoggerFactory.getLogger(CityController.class);
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
        try {
            Notification notification = cityService.create(city);
            logger.warn("New city added with name - {}. Action successful.", city.getValue());
            return new ResponseEntity<Notification>(notification, HttpStatus.OK);
        } catch(Exception e) {
            logger.error("New city with name - {} couldn't be added, exception occured: {} ", city.getValue(), e.toString());
            return new ResponseEntity<Notification>(new Notification("Server error!"),HttpStatus.BAD_REQUEST);
        }
    }
}
