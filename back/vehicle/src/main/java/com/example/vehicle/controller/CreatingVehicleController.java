package com.example.vehicle.controller;

import com.example.vehicle.dto.NewVehicleDTO;
import com.example.vehicle.model.Notification;
import com.example.vehicle.service.CreatingVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("creatingVehicle")
public class CreatingVehicleController {

    @Autowired
    private CreatingVehicleService creatingVehicleService;

    /**
     * POST /server/create
     *
     * @return return status of creating vehicle request
     */
    @PostMapping(value = "/create", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> create(@RequestBody NewVehicleDTO newVehicleDTO) throws Exception {
        Notification notification = creatingVehicleService.createVehicle(newVehicleDTO);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

}
