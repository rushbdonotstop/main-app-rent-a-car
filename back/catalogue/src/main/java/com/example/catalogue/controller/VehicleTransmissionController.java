package com.example.catalogue.controller;

import com.example.catalogue.model.Notification;
import com.example.catalogue.model.VehicleStyle;
import com.example.catalogue.model.VehicleTransmission;
import com.example.catalogue.service.VehicleTransmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("catalogue/vehicleTransmission")
public class VehicleTransmissionController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private VehicleTransmissionService vehicleTransmissionService;


    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleTransmission>> getAllTransmissions() {
        try {
            return new ResponseEntity<>(vehicleTransmissionService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * GET server/catalogue/vehicleTransmission/{id}
     *
     * @return return a vehicle transmission
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleTransmission> getOneVehicleTransmission(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(vehicleTransmissionService.findOne(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * DELETE server/catalogue/vehicleTransmission/{id}
     *
     * @return return a notification
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> deleteVehicleTransmission(@PathVariable Long id) {
        try {
            ResponseEntity<List> response = restTemplate
                    .exchange("http://vehicle/search/transmissionType/" + id, HttpMethod.GET, null, List.class);
            List<Long> vehicleList = response.getBody();
            System.out.println("OVDE JE DUZINA LISTE " + vehicleList.size());
            if(vehicleList.size() != 0) {
                return new ResponseEntity<>(new Notification("There is a vehicle registered with transmission type id " + id + "\nTransmission type wasn't deleted.", false), HttpStatus.CONFLICT);
            }

            vehicleTransmissionService.deleteOne(id);
            return new ResponseEntity<>(new Notification("Successfully deleted vehicle transmission id = " + id, true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * PUT server/catalogue/vehicleFuelType
     *
     * @return return status of creating a vehicle model request
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> putVehicleTransmission(@PathVariable Long id, @RequestBody VehicleTransmission vehicleTransmission) {
        try {
            vehicleTransmissionService.change(id, vehicleTransmission);

            return new ResponseEntity<>(new Notification("Vehicle transmission changed to " + vehicleTransmission.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * POST server/catalogue/vehicleStyle/createReturnObject
     *
     * @return return object of creating vehicle fuel type request
     */
    @PostMapping(value="/createReturnObject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleTransmission> createReturnObject(@RequestBody VehicleTransmission vehicleTransmission) {
        return new ResponseEntity<VehicleTransmission>(vehicleTransmissionService.createTransmission(vehicleTransmission), HttpStatus.OK);
    }
  
  
     * POST server/catalogue/vehicleTransmission
     *
     * @return return status of creating transmission type request
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> postVehicleFuelType(@RequestBody VehicleTransmission vehicleTransmission) {
        try {
            vehicleTransmissionService.addNew(vehicleTransmission);

            return new ResponseEntity<>(new Notification("Successfully added vehicle transmission type " + vehicleTransmission.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

}
