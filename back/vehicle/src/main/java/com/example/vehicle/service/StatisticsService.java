package com.example.vehicle.service;

import com.example.vehicle.dto.catalogue.VehicleMake;
import com.example.vehicle.dto.catalogue.VehicleModel;
import com.example.vehicle.dto.review.Review;
import com.example.vehicle.dto.review.Status;
import com.example.vehicle.dto.statistics.VehicleWithRating;
import com.example.vehicle.dto.statistics.StatisticsDTO;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    SearchVehicleService searchVehicleService;

    public StatisticsDTO getMostMileage(StatisticsDTO dto, Long userId, List<VehicleMake> vehicleMakeList, List<VehicleModel> vehicleModelList) {
        int mostMileage = 0;
        Vehicle vehicleWithMostMileage = new Vehicle();

        List<Vehicle> vehicleList = vehicleRepository.findAll();
        System.err.println("VELICINA LISTE VOZILA JE: " + vehicleList.size());
        for (Vehicle vehicle : vehicleList) {
            if (vehicle.getUserId().equals(userId)) {
                System.err.println("USAO U PRVI IF ZA KORISNIKA");
                if (vehicle.getMileage() > mostMileage) {
                    System.err.println("USAO U IF ZA MILEAGE");
                    mostMileage = vehicle.getMileage();
                    vehicleWithMostMileage = vehicle;
                }
            }
        }
        String vehicleModel = "";
        String vehicleMake= "";
        for (VehicleModel model : vehicleModelList) {
            if (model.getId().equals(vehicleWithMostMileage.getModelId())) {
                vehicleModel = model.getValue();
                break;
            }
        }
        for (VehicleMake make : vehicleMakeList) {
            if (make.getId().equals(vehicleWithMostMileage.getMakeId())) {
                vehicleMake = make.getValue();
            }
        }
        dto.setMostMileage(mostMileage);
        dto.setMakeModelForMileage(vehicleMake + " " + vehicleModel);

        return dto;
    }

    public StatisticsDTO getBestRating (StatisticsDTO dto, Long userId, List<VehicleMake> vehicleMakeList, List<VehicleModel> vehicleModelList, List<Review> reviewList) {
        float bestRating = 0;
        Vehicle vehicleWithBestRating = new Vehicle();

        int mostReviews = 0;
        Vehicle vehicleWithMostReviews = new Vehicle();

        List<Vehicle> vehicleList = vehicleRepository.findAllByUserId(userId);
        List<VehicleWithRating> vehicleWithRatingList = new ArrayList<>();
        List<Review> temp = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            for (Review review : reviewList) {
                if (review.getVehicleId().equals(vehicle.getId()) && review.getStatus().equals(Status.APPROVED)) {
                    temp.add(review);
                }
            }
            VehicleWithRating vwr = new VehicleWithRating();
            vwr.setVehicle(vehicle);
            float rating = searchVehicleService.calculateAverageRating(temp, vehicle.getId());
            vwr.setRating(rating);
            vwr.setNumberOfReviews(temp.size());
            vehicleWithRatingList.add(vwr);
            temp = new ArrayList<>();
        }

        for (VehicleWithRating vehicle : vehicleWithRatingList) {
            if (vehicle.getRating() > bestRating) {
                vehicleWithBestRating = vehicle.getVehicle();
                bestRating = vehicle.getRating();
            }

            if (vehicle.getNumberOfReviews() > mostReviews) {
                vehicleWithMostReviews = vehicle.getVehicle();
                mostReviews = vehicle.getNumberOfReviews();
            }
        }

        String vehicleModel = "";
        String vehicleMake= "";
        for (VehicleModel model : vehicleModelList) {
            if (model.getId().equals(vehicleWithBestRating.getModelId())) {
                vehicleModel = model.getValue();
                break;
            }
        }
        for (VehicleMake make : vehicleMakeList) {
            if (make.getId().equals(vehicleWithBestRating.getMakeId())) {
                vehicleMake = make.getValue();
            }
        }

        String vehicleModelReviews = "";
        String vehicleMakeReviews= "";
        for (VehicleModel model : vehicleModelList) {
            if (model.getId().equals(vehicleWithMostReviews.getModelId())) {
                vehicleModelReviews = model.getValue();
                break;
            }
        }
        for (VehicleMake make : vehicleMakeList) {
            if (make.getId().equals(vehicleWithMostReviews.getMakeId())) {
                vehicleMakeReviews = make.getValue();
            }
        }

        dto.setBestRating(bestRating);
        dto.setMakeModelForBestRating(vehicleMake + " " + vehicleModel);
        dto.setMostReviews(mostReviews);
        dto.setMakeModelForMostReviews(vehicleMakeReviews + " " + vehicleModelReviews);


        return dto;
    }


}
