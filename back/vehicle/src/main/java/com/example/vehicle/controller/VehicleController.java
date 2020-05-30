package com.example.vehicle.controller;

import com.example.vehicle.dto.VehicleDTO;
import com.example.vehicle.dto.VehicleDetailsDTO;
import com.example.vehicle.dto.VehicleMainViewDTO;
import com.example.vehicle.dto.pricelist.PriceListDTO;
import com.example.vehicle.model.Notification;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.service.VehicleService;
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
@RequestMapping("vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    RestTemplate restTemplate;

    /**
     * POST /server/vehicle
     *
     * @return return status of creating vehicle request
     */
    @PostMapping(value = "", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> create(@RequestBody VehicleDTO vehicleDTO) throws Exception {
        Notification notification = vehicleService.createVehicle(vehicleDTO);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    /**
     * PUT /server/vehicle
     *
     * @return return status of updating vehicle request
     */
    @PutMapping(value = "", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> update(@RequestBody VehicleDTO vehicleDTO) throws Exception {
        Notification notification = vehicleService.createVehicle(vehicleDTO);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    /**
     * GET /server/vehicle/all
     *
     * @return return all vehicles
     */
    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleMainViewDTO>> getAll() throws Exception {
        List<VehicleMainViewDTO> vehicleMainViewDTOList = vehicleService.getAll();
        return new ResponseEntity<List<VehicleMainViewDTO>>(vehicleMainViewDTOList, HttpStatus.OK);
    }

    /**
     * GET /server/vehicle/details
     *
     * @return return vehicle details
     */
    @GetMapping(value = "details", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleDetailsDTO> getVehicleDetails(@RequestParam(value="vehicleId", required = true) Long vehicleId) throws Exception {
        VehicleDetailsDTO vehicleDetails = vehicleService.getVehicleDetails(vehicleId);
        return new ResponseEntity<VehicleDetailsDTO>(vehicleDetails, HttpStatus.OK);
    }

//    /**
//     * GET /server/vehicle/test
//     *
//     * @return return all pricelist objects
//     */
//    @GetMapping(value = "test", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<PriceListDTO>> getPricelists() throws Exception {
//        System.out.println("Getting all pricelists");
//        List<PriceListDTO> response = restTemplate.exchange("http://pricelist/pricelist/all",
//                HttpMethod.GET, null, new ParameterizedTypeReference<List<PriceListDTO>>() {}).getBody();
//        System.out.println(response);
//        return new ResponseEntity<List<PriceListDTO>>(response, HttpStatus.OK);
//    }

}
