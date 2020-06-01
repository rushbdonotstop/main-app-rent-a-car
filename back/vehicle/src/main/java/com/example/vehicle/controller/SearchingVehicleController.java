package com.example.vehicle.controller;

import com.example.vehicle.dto.Pricelist;
import com.example.vehicle.dto.VehicleMainViewDTO;
import com.example.vehicle.dto.VehicleMake;
import com.example.vehicle.dto.VehicleModel;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.service.SearchingVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("searchingVehicle")
public class SearchingVehicleController {

    @Autowired
    private SearchingVehicleService searchingVehicleService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleMainViewDTO>> parameterizedSearch() throws Exception {
        List<Pricelist> pricelist = (this.getPricelists()).getBody();

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    public ResponseEntity<List<Pricelist>> getPricelists() throws Exception {
        System.out.println("Getting all pricelists");
        List<Pricelist> response = restTemplate.exchange("http://pricelist/pricelist/all",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Pricelist>>() {}).getBody();

        return new ResponseEntity<List<Pricelist>>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<VehicleMake>> getVehicleMakes() throws Exception {
        System.out.println("Getting all vehicle makes");
        List<VehicleMake> response = restTemplate.exchange("http://catalogue/catalogue/vehicleMake",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<VehicleMake>>() {}).getBody();

        return new ResponseEntity<List<VehicleMake>>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<VehicleModel>> getVehicleModels() throws Exception {
        System.out.println("Getting all vehicle models");
        List<VehicleModel> response = restTemplate.exchange("http://catalogue/catalogue/vehicleModel/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<VehicleModel>>() {}).getBody();
        return new ResponseEntity<List<VehicleModel>>(response, HttpStatus.OK);
    }
}
