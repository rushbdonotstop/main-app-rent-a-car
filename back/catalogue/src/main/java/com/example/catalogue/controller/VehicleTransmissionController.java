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
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.util.List;

@RestController
@RequestMapping("catalogue/vehicleTransmission")
public class VehicleTransmissionController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private VehicleTransmissionService vehicleTransmissionService;

    Logger logger = LoggerFactory.getLogger(VehicleTransmissionController.class);

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

            if(vehicleList.size() != 0) {
                logger.info("Admin requested to delete vehicle transmission type with id - {} - but there is vehicle registered with that type. Action unsuccessful.", id);
                return new ResponseEntity<>(new Notification("There is a vehicle registered with transmission type id " + id + "\nTransmission type wasn't deleted.", false), HttpStatus.CONFLICT);
            }
            logger.warn("Admin requested to delete vehicle transmission type with id - {}. Action successful.", id);
            vehicleTransmissionService.deleteOne(id);
            return new ResponseEntity<>(new Notification("Successfully deleted vehicle transmission id = " + id, true), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Admin requested to delete vehicle transmission type with id - {} - exception occured: {} ", id, e.toString());
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
            String transBefore = vehicleTransmissionService.findOne(id).getValue();
            vehicleTransmissionService.change(id, vehicleTransmission);
            logger.warn("Admin updated vehicle transmission type with id - {}, from {} to {}. Action successful.", id, transBefore, vehicleTransmission.getValue());
            return new ResponseEntity<>(new Notification("Vehicle transmission changed to " + vehicleTransmission.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Admin requested to update vehicle transmission type with id - {} - exception occured: {} ", id, e.toString());
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
        logger.warn("Admin requested to create vehicle transmission type with name - {}. Action successful.", vehicleTransmission.getValue());
        return new ResponseEntity<VehicleTransmission>(vehicleTransmissionService.createTransmission(vehicleTransmission), HttpStatus.OK);
    }

    /**
     * POST server/catalogue/vehicleTransmission
     *
     * @return return status of creating transmission type request
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> postVehicleFuelType(@RequestBody VehicleTransmission vehicleTransmission) {
        try {
            vehicleTransmissionService.addNew(vehicleTransmission);
            logger.warn("Admin requested to create vehicle transmission type with name - {}. Action successful.", vehicleTransmission.getValue());
            return new ResponseEntity<>(new Notification("Successfully added vehicle transmission type " + vehicleTransmission.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Admin requested to create vehicle transmission type with name - {} - exception occured: {} ", vehicleTransmission.getValue(), e.toString());
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

}
