package com.example.location.controller;

import com.example.location.model.Notification;
import com.example.location.service.StreetService;
import com.example.location.model.Street;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("location/street")
public class StreetController {

    @Autowired
    private StreetService streetService;

    /**
     * GET /server/location/street
     *
     * @return return all streets
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Street>> getAll() throws Exception {
        List<Street> streets = streetService.getAll();
        return new ResponseEntity<List<Street>>(streets, HttpStatus.OK);
    }

    /**
     * GET /server/location/street/{id}
     *
     * @return return street with specific id
     */
    @GetMapping(value = "/{streetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Street> get(@PathVariable Long streetId) throws Exception {
        Street street = streetService.get(streetId);
        return new ResponseEntity<Street>(street, HttpStatus.OK);
    }

    /**
     * DELETE /server/location/street/{id}
     *
     * @return return status of delete street action
     */
    @DeleteMapping(value = "/{streetId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> delete(@PathVariable Long streetId) throws Exception {
        Notification notification = streetService.delete(streetId);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    /**
     * CREATE /server/location/street
     *
     * @return return status of create street
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> create(@RequestBody Street street) throws Exception {
        Notification notification = streetService.create(street);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }
}
