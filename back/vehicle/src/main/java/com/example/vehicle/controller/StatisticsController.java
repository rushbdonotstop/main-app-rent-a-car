package com.example.vehicle.controller;

import com.example.vehicle.dto.catalogue.VehicleMake;
import com.example.vehicle.dto.catalogue.VehicleModel;
import com.example.vehicle.dto.review.Review;
import com.example.vehicle.dto.statistics.StatisticsDTO;
import com.example.vehicle.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    StatisticsService statisticsService;

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StatisticsDTO> getConversations(@PathVariable Long userId) throws Exception {

        List<VehicleMake> vehicleMakeList = (this.getVehicleMakes()).getBody();
        List<VehicleModel> vehicleModelList = (this.getVehicleModels()).getBody();
        List<Review> reviewList = (this.getReviews()).getBody();
        StatisticsDTO stat = new StatisticsDTO();
        stat = statisticsService.getMostMileage(stat, userId, vehicleMakeList, vehicleModelList);
        stat = statisticsService.getBestRating(stat, userId, vehicleMakeList, vehicleModelList, reviewList);


        return new ResponseEntity<StatisticsDTO>(stat, HttpStatus.OK);

    }

    public ResponseEntity<List<VehicleMake>> getVehicleMakes() throws Exception {
        System.out.println("Getting all vehicle makes");
        List<VehicleMake> response = restTemplate.exchange("http://catalogue/catalogue/vehicleMake",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<VehicleMake>>() {}).getBody();

        return new ResponseEntity<List<VehicleMake>>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<VehicleModel>> getVehicleModels() throws Exception {
        System.out.println("Getting all vehicle models");
        List<VehicleModel> response = restTemplate.exchange("http://catalogue/catalogue/vehicleModel/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<VehicleModel>>() {}).getBody();

        return new ResponseEntity<List<VehicleModel>>(response, HttpStatus.OK);
    }

    public ResponseEntity<List<Review>> getReviews() throws Exception {
        System.out.println("Getting all reviews");
        List<Review> response = restTemplate.exchange("http://review/review/",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Review>>() {}).getBody();

        return new ResponseEntity<List<Review>>(response, HttpStatus.OK);
    }

}
