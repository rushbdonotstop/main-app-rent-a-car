package com.example.vehicle.controller;

import com.example.vehicle.dto.VehicleDetailsDTO;
import com.example.vehicle.dto.VehicleMainViewDTO;
import com.example.vehicle.model.Notification;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.QueryParam;
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
     * @return return status of creating vehicle
     */
    @PostMapping(value = "", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> create(@RequestBody Vehicle vehicle) throws Exception {
        Vehicle vehicle1 = vehicleService.create(vehicle);
        return new ResponseEntity<>(vehicle1, HttpStatus.OK);
    }

    /**
     * PUT /server/vehicle
     *
     * @return return status of updating vehicle
     */
    @PutMapping(value = "", consumes= MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> update(@RequestBody Vehicle vehicle) throws Exception {
        Notification notification = vehicleService.update(vehicle);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    /**
     * GET /server/vehicle
     *
     * @return return all vehicles
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vehicle>> getAll() throws Exception {
        List<Vehicle> vehicles = vehicleService.getAll();
        return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
    }

    /**
     * GET /server/vehicle/{vehicleId}
     *
     * @return return vehicle
     */
    @GetMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> get(@PathVariable Long vehicleId) throws Exception {
        Vehicle vehicle = vehicleService.get(vehicleId);
        return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
    }

    /**
     * DELETE /server/vehicle/
     *
     * @return return delete status
     */
    @DeleteMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> delete(@PathVariable Long vehicleId) throws Exception {
        Notification notification = vehicleService.delete(vehicleId);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    /**
     * GET /server/vehicle/exists
     *
     * @return return true if vehicle exists
     */
    @GetMapping(value = "exists/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> exists(@PathVariable Long vehicleId) throws Exception {
        Boolean exists = vehicleService.exists(vehicleId);
        return new ResponseEntity<Boolean>(exists, HttpStatus.OK);
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
