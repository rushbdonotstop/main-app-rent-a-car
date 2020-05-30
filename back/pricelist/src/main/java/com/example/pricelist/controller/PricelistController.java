package com.example.pricelist.controller;

import com.example.pricelist.model.Pricelist;
import com.example.pricelist.service.PricelistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pricelist")
public class PricelistController {

    @Autowired
    private PricelistService priceListService;

    /**
     * GET /server/vehicle/test
     *
     * @return return all pricelist objects
     */
    @GetMapping(value = "all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Pricelist>> getPricelists() throws Exception {
        System.out.println("Getting all pricelists from pricelist service");
        List<Pricelist> response = priceListService.getAll();
        return new ResponseEntity<List<Pricelist>>(response, HttpStatus.OK);
    }
}
