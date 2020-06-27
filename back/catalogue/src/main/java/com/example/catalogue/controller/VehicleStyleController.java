package com.example.catalogue.controller;

import com.example.catalogue.model.Notification;
import com.example.catalogue.model.VehicleModel;
import com.example.catalogue.model.VehicleStyle;
import com.example.catalogue.service.VehicleStyleService;
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
@RequestMapping("catalogue/vehicleStyle")
public class VehicleStyleController {
    @Autowired
    private VehicleStyleService vehicleStyleService;

    @Autowired
    private RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(VehicleStyleController.class);

    /**
     * GET server/catalogue/vehicleStyle/{id}
     *
     * @return return a vehicle style
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleStyle> getOneVehicleStyle(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(vehicleStyleService.findOneStyle(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * DELETE server/catalogue/vehicleStyle/{id}
     *
     * @return return notification
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> deleteVehicleStyle(@PathVariable Long id) {
        try {
            ResponseEntity<List> response = restTemplate
                    .exchange("http://vehicle/search/vehicleStyle/" + id, HttpMethod.GET, null, List.class);
            List<Long> vehicleList = response.getBody();
            if(vehicleList.size() != 0) {
                logger.info("Admin requested to delete vehicle style with id - {} - but there is vehicle registered with that style. Action unsuccessful.", id);
                return new ResponseEntity<>(new Notification("There is a vehicle registered with vehicle style id " + id + "\nVehicle style wasn't deleted.", false), HttpStatus.CONFLICT);
            }

            vehicleStyleService.deleteOneStyle(id);
            logger.warn("Admin requested to delete vehicle style with id - {}. Action successful.", id);
            return new ResponseEntity<>(new Notification("Successfully deleted vehicle style id = " + id, true), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Admin requested to delete vehicle style with id - {} - exception occured: {} ", id, e.toString());
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * PUT server/catalogue/vehicleStyle
     *
     * @return return status of creating a vehicle style request
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> putVehicleStyle(@PathVariable Long id, @RequestBody VehicleStyle vehicleStyle) {
        try {
            String styleBefore = vehicleStyleService.findOneStyle(id).getValue();
            vehicleStyleService.changeStyle(id, vehicleStyle);
            logger.warn("Admin updated vehicle model with id {}, from {} to {}. Action successful.", id, styleBefore, vehicleStyle.getValue());
            return new ResponseEntity<>(new Notification("Vehicle style changed to " + vehicleStyle.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Admin requested to update vehicle style with id - {} - exception occured: {} ", id, e.toString());
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * GET server/catalogue/vehicleStyle
     *
     * @return return all vehicle styles
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getVehicleStyle() {
        List<VehicleStyle> vehicleStyleList = vehicleStyleService.getAllStyles();
        if(vehicleStyleList.size()==0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vehicleStyleList, HttpStatus.OK);
    }

    /**
     * POST server/catalogue/vehicleStyle
     *
     * @return return status of creating vehicle style request
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> postVehicleFuelType(@RequestBody VehicleStyle vehicleStyle) {
        try {
            vehicleStyleService.addNewStyle(vehicleStyle);
            logger.warn("Admin requested to create vehicle style with name - {}. Action successful.", vehicleStyle.getValue());
            return new ResponseEntity<>(new Notification("Successfully added a vehicle style " + vehicleStyle.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Admin requested to create vehicle style with name - {} - exception occured: {} ", vehicleStyle.getValue(), e.toString());
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * POST server/catalogue/vehicleStyle/createReturnObject
     *
     * @return return object of creating vehicle fuel type request
     */
    @PostMapping(value="/createReturnObject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleStyle> createReturnObject(@RequestBody VehicleStyle vehicleStyle) {
        logger.warn("Admin requested to create vehicle style with name - {}. Action successful.", vehicleStyle.getValue());
        return new ResponseEntity<VehicleStyle>(vehicleStyleService.createStyle(vehicleStyle), HttpStatus.OK);
    }
}
