package com.example.catalogue.controller;


import com.example.catalogue.model.Notification;
import com.example.catalogue.model.VehicleMake;
import com.example.catalogue.model.VehicleModel;
import com.example.catalogue.service.VehicleMakeService;
import com.example.catalogue.service.VehicleModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
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
@RequestMapping("catalogue/vehicleModel")
public class VehicleModelController {
    @Autowired
    private VehicleModelService vehicleModelService;

    @Autowired
    private VehicleMakeService vehicleMakeService;

    @Autowired
    private RestTemplate restTemplate;

    Logger logger = LoggerFactory.getLogger(VehicleModelController.class);

    /**
     * GET server/catalogue/vehicleModel/{id}
     *
     * @return return a vehicle model
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleModel> getOneVehicleModel(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(vehicleModelService.findOneModel(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/byMake/{makeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleModel>> getModelsByMake(@PathVariable Long makeId) {
        try {
            return new ResponseEntity<>(vehicleModelService.getModelsByMake(vehicleMakeService.findOneMake(makeId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    /**
     * DELETE server/catalogue/vehicleModel/{id}
     *
     * @return return a notification
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> deleteVehicleModel(@PathVariable Long id) {
        try {
            ResponseEntity<List> response = restTemplate
                    .exchange("http://vehicle/search/model/" + id, HttpMethod.GET, null, List.class);
            List<Long> vehicleList = response.getBody();
            if(vehicleList.size() != 0) {
                logger.info("Admin requested to delete vehicle model with id - {} - but there is vehicle registered with that model. Action unsuccessful.", id);
                return new ResponseEntity<>(new Notification("There is a vehicle registered with model id " + id + "\nModel wasn't deleted.", false), HttpStatus.CONFLICT);
            }
            vehicleModelService.deleteOneModel(id);
            logger.warn("Admin requested to delete vehicle model with id - {}. Action successful.", id);
            return new ResponseEntity<>(new Notification("Successfully deleted vehicle model id = " + id, true), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Admin requested to delete vehicle model with id - {} - exception occured: {} ", id, e.toString());
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * PUT server/catalogue/vehicleFuelType
     *
     * @return return status of creating a vehicle model request
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> putVehicleModel(@PathVariable Long id, @RequestBody VehicleModel vehicleModel) {
        try {
            String modelBefore = vehicleModelService.findOneModel(id).getValue();
            vehicleModelService.changeModel(id, vehicleModel);
            logger.warn("Admin updated vehicle model with id {}, from {} to {}. Action successful.", id, modelBefore, vehicleModel.getValue());
            return new ResponseEntity<>(new Notification("Vehicle model changed to " + vehicleModel.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Admin requested to update vehicle model with id - {} - exception occured: {} ", id, e.toString());
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * GET server/catalogue/vehicleModel
     *
     * @return return all vehicle models
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List> getVehicleModels() {
        List<VehicleModel> vehicleModelList = vehicleModelService.getAllModels();
        if(vehicleModelList.size()==0) {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(vehicleModelList, HttpStatus.OK);
    }

    /**
     * POST server/catalogue/vehicleModel
     *
     * @return return status of creating vehicle model request
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> postVehicleModel(@RequestBody VehicleModel vehicleModel) {
        try {
            vehicleModelService.addNewModel(vehicleModel);
            logger.warn("Admin requested to create vehicle model with name - {}. Action successful.", vehicleModel.getValue());
            return new ResponseEntity<>(new Notification("Successfully added vehicle model " + vehicleModel.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Admin requested to create vehicle model with name - {} - exception occured: {} ", vehicleModel.getValue(), e.toString());
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }

    /**
     * POST server/catalogue/vehicleModel/createReturnObject
     *
     * @return return object of creating vehicle fuel type request
     */
    @PostMapping(value="/createReturnObject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleModel> createReturnObject(@RequestBody VehicleModel vehicleModel) {
        logger.warn("Admin requested to create vehicle model with name - {}. Action successful.", vehicleModel.getValue());
        return new ResponseEntity<VehicleModel>(vehicleModelService.createModel(vehicleModel), HttpStatus.OK);
    }
}
