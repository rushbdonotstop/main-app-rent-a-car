package com.example.pricelist.controller;

import com.example.pricelist.dto.MinAndMaxPricesDTO;
import com.example.pricelist.model.Notification;
import com.example.pricelist.model.Pricelist;
import com.example.pricelist.service.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("pricelist")
public class PricelistController {

    @Autowired
    private PricelistService priceListService;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * GET /server/pricelist
     *
     * @return return all pricelist for vehicle
     */
    @GetMapping(value = "/{vehicleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pricelist>> getAllByVehicle(@PathVariable Long vehicleId) throws Exception {
        List<Pricelist> pricelists = priceListService.getAllByVehicle(vehicleId);
        return new ResponseEntity<List<Pricelist>>(pricelists, HttpStatus.OK);
    }

    /**
     * GET /server/pricelist
     *
     * @return return all pricelist
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pricelist>> getAll() throws Exception {
        List<Pricelist> pricelists = priceListService.getAll();
        return new ResponseEntity<List<Pricelist>>(pricelists, HttpStatus.OK);
    }

    /**
     * PUT /server/pricelist
     *
     * @return status of updating pricelist
     */
    @PutMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> update(@RequestBody List<Pricelist> pricelists, @RequestParam(value="startDate", required = true)
            LocalDate startDate, @RequestParam(value="endDate", required = true) LocalDate endDate) throws Exception {
        Boolean exists = restTemplate.exchange("http://vehicle/vehicle/exists/" + pricelists.get(0).getVehicleId(),
                HttpMethod.GET, null, new ParameterizedTypeReference<Boolean>() {}).getBody();
        Notification notification = new Notification("Vehicle id does not exist.");
        if (exists){
            notification = priceListService.updatePricelists(pricelists, startDate, endDate);
        }
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    /**
     * POST /server/pricelist
     *
     * @return status of creating pricelist
     */
    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> create(@RequestBody List<Pricelist> pricelists, @RequestParam(value="startDate", required = true)
            LocalDate startDate, @RequestParam(value="endDate", required = true) LocalDate endDate) throws Exception {
        Boolean exists = restTemplate.exchange("http://vehicle/vehicle/exists/" + pricelists.get(0).getVehicleId(),
                HttpMethod.GET, null, new ParameterizedTypeReference<Boolean>() {}).getBody();
        Notification notification = new Notification("Vehicle id does not exist.");
        if (exists){
            notification = priceListService.createPricelists(pricelists, startDate, endDate);
        }
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    /**
     * DELETE /server/pricelist
     *
     * @return status of deleting pricelist
     */
    @DeleteMapping(value = "/{pricelistId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Notification> delete(@PathVariable Long pricelistId) throws Exception {
        Notification notification = priceListService.deletePricelist(pricelistId);
        return new ResponseEntity<Notification>(notification, HttpStatus.OK);
    }

    @GetMapping(value = "/minAndMax", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MinAndMaxPricesDTO> getMinAndMax() throws Exception {
        return new ResponseEntity<>(priceListService.getMinAndMax(), HttpStatus.OK);
    }

    @PostMapping(value = "/validatePricelists", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pricelist>> validatePricelists(@RequestBody List<Pricelist> pricelists, @RequestParam(value="startDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime startDate, @RequestParam(value="endDate", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTimev endDate){
        return new ResponseEntity<List<Pricelist>>(priceListService.validatePricelists(pricelists, startDate, endDate), HttpStatus.OK);
    }

}
