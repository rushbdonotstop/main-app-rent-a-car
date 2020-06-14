package com.example.vehicle.controller;

import com.example.vehicle.dto.*;
import com.example.vehicle.dto.catalogue.VehicleMake;
import com.example.vehicle.dto.catalogue.VehicleModel;
import com.example.vehicle.dto.location.Location;
import com.example.vehicle.dto.pricelist.Pricelist;
import com.example.vehicle.dto.request.RequestForVehicleDTO;
import com.example.vehicle.dto.user.UserDTO;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.service.SearchVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("search")
public class SearchVehicleController {

    @Autowired
    private SearchVehicleService searchVehicleService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleMainViewDTO>> parameterizedSearch(@RequestParam(value = "vehicleMake") Long vehicleMake, @RequestParam(value = "vehicleModel") Long vehicleModel, @RequestParam(value = "vehicleStyle") Long vehicleStyle, @RequestParam(value = "vehicleFuel") Long vehicleFuel, @RequestParam(value = "vehicleTransmission") Long vehicleTransmission, @RequestParam(value = "priceLowerLimit") float priceLowerLimit, @RequestParam(value = "priceUpperLimit") float priceUpperLimit, @RequestParam(value = "maxMileage") int maxMileage, @RequestParam(value = "mileageLimit") int mileageLimit, @RequestParam(value = "collisionProtection") Boolean collisionProtection, @RequestParam(value = "childrenSeats") int childrenSeats, @RequestParam(value = "state") String state, @RequestParam(value = "city") String city, @RequestParam(value = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate, @RequestParam(value = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) throws Exception {
        List<Vehicle> vehicleList = searchVehicleService.findAll();

        List<Pricelist> pricelist = (this.getPricelists()).getBody();

        List<VehicleMake> vehicleMakeList = (this.getVehicleMakes()).getBody();

        List<VehicleModel> vehicleModelsList = (this.getVehicleModels()).getBody();

        List<UserDTO> usersList = (this.getUsernames()).getBody();

        List<Location> locations = (this.getLocations()).getBody();

        List<RequestForVehicleDTO> requestsList = (this.getRequests()).getBody();

        List<VehicleMainViewDTO> dtoList = searchVehicleService.parameterizedSearch(vehicleList, requestsList, locations, vehicleMakeList, pricelist, vehicleModelsList, usersList, vehicleMake, vehicleModel, vehicleStyle, vehicleFuel, vehicleTransmission, maxMileage, mileageLimit, collisionProtection, childrenSeats, state, city, priceLowerLimit, priceUpperLimit, startDate, endDate);

        List<VehicleMainViewDTO> vehicleDtoListFinal = searchVehicleService.getNotBlocked(dtoList, usersList);

        System.out.println("DOBRA METODA ALAL TI VERA");

        return new ResponseEntity<>(vehicleDtoListFinal, HttpStatus.OK);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleMainViewDTO>> getAll() throws Exception {
        List<Vehicle> vehicleList = searchVehicleService.findAll();

        List<Pricelist> pricelist = (this.getPricelists()).getBody();

        List<VehicleMake> vehicleMakeList = (this.getVehicleMakes()).getBody();

        List<VehicleModel> vehicleModelsList = (this.getVehicleModels()).getBody();

        List<UserDTO> usersList = (this.getUsernames()).getBody();

        List<VehicleMainViewDTO> vehicleDTOList = searchVehicleService.getAllVehicleMainViewDTO(vehicleList, vehicleMakeList, pricelist, vehicleModelsList, usersList);

        List<VehicleMainViewDTO> vehicleDtoListFinal = searchVehicleService.getNotBlocked(vehicleDTOList, usersList);
        return new ResponseEntity<>(vehicleDtoListFinal, HttpStatus.OK);
    }

    @GetMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDetailsDTO> getIdForDetails(@PathVariable Long vehicleId) throws Exception {

        Vehicle vehicle = searchVehicleService.findOneById(vehicleId);
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

    public ResponseEntity<List<RequestForVehicleDTO>> getRequests() throws Exception {
        System.out.println("Getting all requests");
        List<RequestForVehicleDTO> response = restTemplate.exchange("http://request/request/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<RequestForVehicleDTO>>() {}).getBody();

        return new ResponseEntity<List<RequestForVehicleDTO>>(response, HttpStatus.OK);
    }
}
