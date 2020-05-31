package com.example.catalogue.controller;

import com.example.catalogue.model.*;
import com.example.catalogue.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("catalogue")
public class CatalogueController {

    @Autowired
    private CatalogueService catalogueService;

    /**
     * GET /server/catalogue/make
     *
     * @return return vehicle make
     */
    @GetMapping(value = "make", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleMake> getVehicleMake(@RequestParam(value="makeId", required = true) Long makeId) throws Exception {
        VehicleMake vehicleMake = catalogueService.getVehicleMake(makeId);
        return new ResponseEntity<VehicleMake>(vehicleMake, HttpStatus.OK);
    }

    /**
     * GET /server/catalogue/model
     *
     * @return return vehicle model
     */
    @GetMapping(value = "model", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleModel> getVehicleModel(@RequestParam(value="modelId", required = true) Long modelId) throws Exception {
        VehicleModel vehicleModel = catalogueService.getVehicleModel(modelId);
        return new ResponseEntity<VehicleModel>(vehicleModel, HttpStatus.OK);
    }

    /**
     * GET /server/catalogue/style
     *
     * @return return vehicle style
     */
    @GetMapping(value = "style", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleStyle> getVehicleStyle(@RequestParam(value="styleId", required = true) Long styleId) throws Exception {
        VehicleStyle vehicleStyle = catalogueService.getVehicleStyle(styleId);
        return new ResponseEntity<VehicleStyle>(vehicleStyle, HttpStatus.OK);
    }

    /**
     * GET /server/catalogue/transmission
     *
     * @return return vehicle transmission
     */
    @GetMapping(value = "transmission", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleTransmission> getVehicleTransmission(@RequestParam(value="transmissionId", required = true) Long transmissionId) throws Exception {
        VehicleTransmission vehicleTransmission = catalogueService.getVehicleTransmission(transmissionId);
        return new ResponseEntity<VehicleTransmission>(vehicleTransmission, HttpStatus.OK);
    }

    /**
     * GET /server/catalogue/fuelType
     *
     * @return return vehicle fuel type
     */
    @GetMapping(value = "fuelType", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleFuelType> getVehicleFuelType(@RequestParam(value="fuelTypeId", required = true) Long fuelTypeId) throws Exception {
        VehicleFuelType vehicleFuelType = catalogueService.getVehicleFuelType(fuelTypeId);
        return new ResponseEntity<VehicleFuelType>(vehicleFuelType, HttpStatus.OK);
    }

    /**
     * GET /server/catalogue/makeAll
     *
     * @return return all vehicle makes
     */
    @GetMapping(value = "makeAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleMake>> getVehicleMakeAll() throws Exception {
        List<VehicleMake> vehicleMakeList = catalogueService.getVehicleMakeAll();
        return new ResponseEntity<List<VehicleMake>>(vehicleMakeList, HttpStatus.OK);
    }

    /**
     * GET /server/catalogue/modelAll
     *
     * @return return all vehicle models
     */
    @GetMapping(value = "modelAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleModel>> getVehicleModelAll() throws Exception {
        List<VehicleModel> vehicleModelList = catalogueService.getVehicleModelAll();
        return new ResponseEntity<List<VehicleModel>>(vehicleModelList, HttpStatus.OK);
    }

    /**
     * GET /server/catalogue/styleAll
     *
     * @return return all vehicle styles
     */
    @GetMapping(value = "styleAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleStyle>> getVehicleStyleAll() throws Exception {
        List<VehicleStyle> vehicleStyleList = catalogueService.getVehicleStyleAll();
        return new ResponseEntity<List<VehicleStyle>>(vehicleStyleList, HttpStatus.OK);
    }

    /**
     * GET /server/catalogue/transmissionAll
     *
     * @return return all vehicle transmissions
     */
    @GetMapping(value = "transmissionAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleTransmission>> getVehicleTransmissionAll() throws Exception {
        List<VehicleTransmission> vehicleTransmissionList = catalogueService.getVehicleTransmissionAll();
        return new ResponseEntity<List<VehicleTransmission>>(vehicleTransmissionList, HttpStatus.OK);
    }

    /**
     * GET /server/catalogue/fuelTypeAll
     *
     * @return return all vehicle fuel types
     */
    @GetMapping(value = "fuelTypeAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleFuelType>> getVehicleFuelTypeAll() throws Exception {
        List<VehicleFuelType> vehicleFuelTypeList = catalogueService.getVehicleFuelTypeAll();
        return new ResponseEntity<List<VehicleFuelType>>(vehicleFuelTypeList, HttpStatus.OK);
    }

}
