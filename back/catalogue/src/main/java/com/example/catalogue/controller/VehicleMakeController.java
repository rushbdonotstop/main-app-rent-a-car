package com.example.catalogue.controller;

import com.example.catalogue.model.Notification;
import com.example.catalogue.model.VehicleFuelType;
import com.example.catalogue.model.VehicleMake;
import com.example.catalogue.service.VehicleMakeService;
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
@RequestMapping("catalogue/vehicleMake")
public class VehicleMakeController {
    @Autowired
    private VehicleMakeService vehicleMakeService;

    @Autowired
    private RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(VehicleMakeController.class);

//    /**
//     * GET server/catalogue/vehicleMake/byModel/{id}
//     *
//     * @return return a vehicle make
//     */
//    @GetMapping(value = "byModel/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<VehicleMake> getOneVehicleMakeByModel(@PathVariable String id) {
//        try {
//            return new ResponseEntity<>(vehicleMakeService.findOneMakeByModel(id), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
//        }
//    }

    /**
     * GET server/catalogue/vehicleMake/{id}
     *
     * @return return a vehicle make
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleMake> getOneVehicleMake(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(vehicleMakeService.findOneMake(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * DELETE server/catalogue/vehicleMake/{id}
     *
     * @return return notification
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> deleteVehicleMake(@PathVariable Long id) {
        try {
            ResponseEntity<List> response = restTemplate
                    .exchange("http://vehicle/search/make/" + id, HttpMethod.GET, null, List.class);
            List<Long> vehicleList = response.getBody();
            if(vehicleList.size() != 0) {
                logger.info("Admin requested to delete vehicle make with id - {} - but there is vehicle registered with that make. Action unsuccessful.", id);
                return new ResponseEntity<>(new Notification("There is a vehicle registered with make id " + id + "\nMake wasn't deleted.", false), HttpStatus.CONFLICT);
            }

            vehicleMakeService.deleteOneMake(id);
            logger.warn("Admin requested to delete vehicle make  with id - {}. Action successful.", id);
            return new ResponseEntity<>(new Notification("Successfully deleted vehicle make id = " + id, true), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Admin requested to delete vehicle make with id - {} - exception occured: {} ", id, e.toString());
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * PUT server/catalogue/vehicleFuelType
     *
     * @return return status of creating vehicle request
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> putVehicleMake(@PathVariable Long id, @RequestBody VehicleMake vehicleMake) {
        try {
            String vehicleMakeBefore = vehicleMakeService.findOneMake(id).getValue();
            vehicleMakeService.changeMake(id, vehicleMake);
            logger.warn("Admin updated vehicle make with id {}, from {} to {}. Action successful.", id, vehicleMakeBefore, vehicleMake.getValue());
            return new ResponseEntity<>(new Notification("Vehicle make changed to " + vehicleMake.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Admin requested to update vehicle make with id - {} - exception occured: {} ", id, e.toString());
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * GET server/catalogue/vehicleFuelType
     *
     * @return return all vehicle fuel types
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getAllMakes() {
        List<VehicleMake> vehicleMakeList = vehicleMakeService.getAllMakes();
        if(vehicleMakeList.size()==0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vehicleMakeList, HttpStatus.OK);
    }


    /**
     * POST server/catalogue/vehicleMake
     *
     * @return return status of creating vehicle make request
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> postVehicleMake(@RequestBody VehicleMake vehicleMake) {
        try {
            vehicleMakeService.addNewMake(vehicleMake);
            logger.warn("Admin requested to create vehicle make with name - {}. Action successful.", vehicleMake.getValue());
            return new ResponseEntity<>(new Notification("Successfully added vehicle make " + vehicleMake.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Admin requested to create vehicle make with name - {} - exception occured: {} ", vehicleMake.getValue(), e.toString());
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * POST server/catalogue/vehicleMake/createReturnObject
     *
     * @return return object of creating vehicle fuel type request
     */
    @PostMapping(value="/createReturnObject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleMake> createReturnObject(@RequestBody VehicleMake vehicleMake) {
        logger.warn("Admin requested to create vehicle make with name - {}. Action successful.", vehicleMake.getValue());
        return new ResponseEntity<VehicleMake>(vehicleMakeService.createMake(vehicleMake), HttpStatus.OK);
    }
}
