package com.example.location.controller;

import com.example.location.model.Notification;
import com.example.location.service.LocationService;
import com.example.location.service.StateService;
import com.example.location.model.City;
import com.example.location.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.SortedSet;

@RestController
@RequestMapping("location")
public class LocationController {

    @Autowired
    private LocationService locationService;


    /**
     * GET /server/location/
     *
     * @return return all locations
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Location>> getAll() throws Exception {
        List<Location> locations = locationService.getAll();
        return new ResponseEntity<List<Location>>(locations, HttpStatus.OK);
    }

    /**
     * GET /server/location/{id}
     *
     * @return return vehicle location with specific id
     */
    @GetMapping(value = "/{locationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Location> get(@PathVariable Long locationId) throws Exception {
        Location location = locationService.get(locationId);
        return new ResponseEntity<Location>(location, HttpStatus.OK);
    }

    @GetMapping(value = "citiesByState/{stateId}", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<SortedSet<City>> getCitiesByState(@PathVariable Long stateId) throws Exception {
        SortedSet<City> cities = locationService.getCitiesByState(stateId);
            return new ResponseEntity<SortedSet<City>>(cities, HttpStatus.OK);
    }

    /**
     * GET /server/location/find
     * params stateId, cityId, streetId
     *
     * @return return vehicle location
     */
    @GetMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> find(@RequestParam(value="state", required = true) String state, @RequestParam(value="city", required = true) String city, @RequestParam(value="street", required = true) String street) throws Exception {
        Long locationId = locationService.find(state, city, street);
        return new ResponseEntity<Long>(locationId, HttpStatus.OK);
    }

    /**
     * DELETE /server/location/{id}
     *
     * @return return status of delete location
     */
    @DeleteMapping(value = "/{locationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> delete(@PathVariable Long locationId) throws Exception {
        Notification notification = locationService.delete(locationId);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    /**
     * CREATE /server/location
     *
     * @return return status of create location
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Location> create(@RequestBody Location location) throws Exception {
        Location locationNew = locationService.create(location);

        return new ResponseEntity<Location>(locationNew, HttpStatus.OK);
    }
}
