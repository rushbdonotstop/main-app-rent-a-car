package com.example.location.controller;

import com.example.location.model.Location;
import com.example.location.model.Notification;
import com.example.location.service.LocationService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    /**
     * GET /server/location
     *
     * @return return vehicle location
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Location> getVehicleLocation(@RequestParam(value="locationId", required = true) Long locationId) throws Exception {
        Location location = locationService.getVehicleLocation(locationId);
        return new ResponseEntity<Location>(location, HttpStatus.OK);
    }

    /**
     * GET /server/location
     *
     * @return return vehicle location
     */
    @GetMapping(value = "find", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> findLocation(@RequestParam(value="state", required = true) String state, @RequestParam(value="city", required = true) String city, @RequestParam(value="street", required = true) String street) throws Exception {
        Long locationId = locationService.findLocation(state, city, street);
        return new ResponseEntity<Long>(locationId, HttpStatus.OK);
    }

    /**
     * DELETE /server/location
     *
     * @return return status of delete action
     */
    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> deleteLocation(@RequestParam(value="locationId", required = true) Long locationId) throws Exception {
        Notification notification = locationService.deleteLocation(locationId);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    /**
     * CREATE /server/location
     *
     * @return return status of delete action
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> createLocation(@RequestBody Location location) throws Exception {
        Notification notification = locationService.createLocation(location);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }
}
