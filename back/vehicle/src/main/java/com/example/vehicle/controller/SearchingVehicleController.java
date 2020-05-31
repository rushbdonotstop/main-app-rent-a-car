package com.example.vehicle.controller;

import com.example.vehicle.dto.VehicleDTO;
import com.example.vehicle.dto.VehicleMainViewDTO;
import com.example.vehicle.dto.VehicleSearchDTO;
import com.example.vehicle.model.Notification;
import com.example.vehicle.service.SearchingVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("searchingVehicle")
public class SearchingVehicleController {

    @Autowired
    private SearchingVehicleService searchingVehicleService;

    @PostMapping(value = "", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleMainViewDTO>> parameterizedSearch(@RequestBody VehicleSearchDTO vehicleSearchDTO) throws Exception {

        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
