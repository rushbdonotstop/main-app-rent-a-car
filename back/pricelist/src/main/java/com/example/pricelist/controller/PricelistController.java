package com.example.pricelist.controller;

import com.example.pricelist.model.Notification;
import com.example.pricelist.model.Pricelist;
import com.example.pricelist.service.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pricelist")
public class PricelistController {

    @Autowired
    private PricelistService priceListService;


    /**
     * GET /server/pricelist
     *
     * @return return all pricelist for vehicle
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pricelist>> getAll(@RequestParam(value="vehicleId", required = true) Long vehicleId) throws Exception {
        List<Pricelist> pricelists = priceListService.getAll(vehicleId);
        return new ResponseEntity<List<Pricelist>>(pricelists, HttpStatus.OK);
    }

    /**
     * PUT /server/pricelist
     *
     * @return status of updating pricelist
     */
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> updatePricelist(@RequestBody Pricelist pricelist) throws Exception {
        Notification notification = priceListService.updatePricelist(pricelist);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    /**
     * POST /server/pricelist
     *
     * @return status of creating pricelist
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> createPricelist(@RequestBody Pricelist pricelist) throws Exception {
        Notification notification = priceListService.createPricelist(pricelist);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    /**
     * DELETE /server/pricelist
     *
     * @return status of deleting pricelist
     */
    @DeleteMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> deletePricelist(@RequestParam(value="pricelistId", required = true) Long pricelistId) throws Exception {
        Notification notification = priceListService.deletePricelist(pricelistId);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }
}
