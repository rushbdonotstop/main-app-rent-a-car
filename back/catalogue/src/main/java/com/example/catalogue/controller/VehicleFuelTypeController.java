package com.example.catalogue.controller;

import com.example.catalogue.model.VehicleFuelType;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("catalogue/vehicleFuel")
public class VehicleFuelTypeController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getVehicleFuelType()throws Exception {
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
