package com.example.catalogue.controller;

import com.example.catalogue.model.Notification;
import com.example.catalogue.model.VehicleMake;
import com.example.catalogue.repository.VehicleMakeRepository;
import com.example.catalogue.service.VehicleMakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("catalogue/vehicleMake")
public class VehicleMakeController {
    @Autowired
    private VehicleMakeService vehicleMakeService;

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
    public ResponseEntity<VehicleMake> getOneVehicleMake(@PathVariable String id) {
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
    public ResponseEntity<Notification> deleteVehicleMake(@PathVariable String id) {
        try {
            vehicleMakeService.deleteOneMake(id);
            return new ResponseEntity<>(new Notification("Successfully deleted vehicle make id = " + id, true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.NO_CONTENT);
        }
    }

    /**
     * PUT server/catalogue/vehicleFuelType
     *
     * @return return status of creating vehicle request
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> putVehicleMake(@PathVariable String id, @RequestBody VehicleMake vehicleMake) {
        try {
            vehicleMakeService.changeMake(id, vehicleMake);

            return new ResponseEntity<>(new Notification("Vehicle make changed to " + vehicleMake.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
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

            return new ResponseEntity<>(new Notification("Successfully added vehicle make " + vehicleMake.getValue(), true), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new Notification(e.getMessage(), false), HttpStatus.CONFLICT);
        }
    }
}
