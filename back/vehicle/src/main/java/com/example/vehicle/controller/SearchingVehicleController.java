package com.example.vehicle.controller;

import com.example.vehicle.dto.*;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.service.SearchingVehicleService;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.PathParam;
import java.util.List;

@RestController
@RequestMapping("search")
public class SearchingVehicleController {

    @Autowired
    private SearchingVehicleService searchingVehicleService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleMainViewDTO>> parameterizedSearch(@RequestParam(value = "vehicleMake") Long vehicleMake, @RequestParam(value = "vehicleModel") Long vehicleModel, @RequestParam(value = "vehicleStyle") Long vehicleStyle, @RequestParam(value = "vehicleFuel") Long vehicleFuel, @RequestParam(value = "vehicleTransmission") Long vehicleTransmission, @RequestParam(value = "priceLowerLimit") float priceLowerLimit, @RequestParam(value = "priceUpperLimit") float priceUpperLimit, @RequestParam(value = "maxMileage") int maxMileage, @RequestParam(value = "mileageLimit") int mileageLimit, @RequestParam(value = "collisionProtection") Boolean collisionProtection, @RequestParam(value = "childrenSeats") int childrenSeats, @RequestParam(value = "state") String state, @RequestParam(value = "city") String city) throws Exception {
        List<Vehicle> vehicleList = searchingVehicleService.findAll();

        List<Pricelist> pricelist = (this.getPricelists()).getBody();

        List<VehicleMake> vehicleMakeList = (this.getVehicleMakes()).getBody();

        List<VehicleModel> vehicleModelsList = (this.getVehicleModels()).getBody();

        List<UserDTO> usersList = (this.getUsernames()).getBody();

        List<Location> locations = (this.getLocations()).getBody();

        List<VehicleMainViewDTO> dtoList = searchingVehicleService.parameterizedSearch(vehicleList, locations, vehicleMakeList, pricelist, vehicleModelsList, usersList, vehicleMake, vehicleModel, vehicleStyle, vehicleFuel, vehicleTransmission, maxMileage, mileageLimit, collisionProtection, childrenSeats, state, city, priceLowerLimit, priceUpperLimit);

        return new ResponseEntity<>(dtoList, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleMainViewDTO>> getAll() throws Exception {
        List<Vehicle> vehicleList = searchingVehicleService.findAll();

        List<Pricelist> pricelist = (this.getPricelists()).getBody();

        List<VehicleMake> vehicleMakeList = (this.getVehicleMakes()).getBody();

        List<VehicleModel> vehicleModelsList = (this.getVehicleModels()).getBody();

        List<UserDTO> usersList = (this.getUsernames()).getBody();

        List<VehicleMainViewDTO> vehicleDTOList = searchingVehicleService.getAllVehicleMainViewDTO(vehicleList, vehicleMakeList, pricelist, vehicleModelsList, usersList);
        return new ResponseEntity<>(vehicleDTOList, HttpStatus.OK);
    }

    @GetMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDetailsDTO> getIdForDetails(@PathVariable Long vehicleId) throws Exception {

        Vehicle vehicle = searchingVehicleService.findOneById(vehicleId);
        VehicleDetailsDTO dto = new VehicleDetailsDTO(vehicle);

        return new ResponseEntity<VehicleDetailsDTO>(dto, HttpStatus.OK);

    }

    public ResponseEntity<List<Pricelist>> getPricelists() throws Exception {
        System.out.println("Getting all pricelists");
        List<Pricelist> response = restTemplate.exchange("http://pricelist/pricelist",
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


    public ResponseEntity<List<UserDTO>> getUsernames() throws Exception {
        System.out.println("Getting all usernames");
        List<UserDTO> response = restTemplate.exchange("http://user/user/usernames/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<UserDTO>>() {}).getBody();

        return new ResponseEntity<List<UserDTO>>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<Location>> getLocations() throws Exception {
        System.out.println("Getting all locations");
        List<Location> response = restTemplate.exchange("http://location/location/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Location>>() {}).getBody();

        return new ResponseEntity<List<Location>>(response, HttpStatus.OK);
    }
}
