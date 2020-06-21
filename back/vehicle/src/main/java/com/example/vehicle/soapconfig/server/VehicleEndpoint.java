package com.example.vehicle.soapconfig.server;

import com.example.vehicle.dto.catalogue.forsoap.*;
import com.example.vehicle.dto.location.Location;
import com.example.vehicle.dto.request.RequestForVehicleDTO;
import com.example.vehicle.model.Notification;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.service.VehicleImageService;
import com.example.vehicle.service.VehicleService;
import com.example.vehicle.xmlmodel.pricelist.Pricelist;
import com.example.vehicle.xmlmodel.vehicle.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.ArrayList;
import java.util.List;

@Endpoint
public class VehicleEndpoint {
    private static final String NAMESPACE_URI = "http://rentacar.com/vehicle";

    @Autowired
    VehicleService vehicleService;

    @Autowired
    VehicleImageService vehicleImageService;

    @Autowired
    private RestTemplate restTemplate;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createVehicle")
    @ResponsePayload
    public GetVehicleById createVehicle(@RequestPayload CreateVehicle request) {

        System.out.println("Request from app for method create vehicle; Server sent : " + request.getVehicle().getId());

        GetVehicleById getVehicleById = new GetVehicleById();

        getVehicleById.setVehicleId(request.getVehicle().getId());

        vehicleService.addAgentVehicle(request.getVehicle().toModel(request.getVehicle()));

        System.out.println(getVehicleById.getVehicleId());

        return getVehicleById;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateVehicle")
    @ResponsePayload
    public GetVehicleById updateVehicle(@RequestPayload UpdateVehicle request) {

        System.out.println("Request from app for method update vehicle; Server sent : " + request.getVehicle().getMileage());

        GetVehicleById getVehicleById = new GetVehicleById();
        getVehicleById.setVehicleId(request.getVehicle().getId());

        return getVehicleById;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserById")
    @ResponsePayload
    public GetVehicleById deleteVehicle(@RequestPayload DeleteVehicleById request) {

        System.out.println("Request from app for method delete user; Server sent : " + request.getVehicleId());

        GetVehicleById getVehicleById = new GetVehicleById();
        getVehicleById.setVehicleId(request.getVehicleId());

        return getVehicleById;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createEverythingVehicle")
    @ResponsePayload
    public GetVehicleById createEverythingVehicle(@RequestPayload CreateEverythingVehicle request) {

        System.out.println("Request from app for method create vehicle; Server sent : " + request.getVehicle().getMileage());

        GetVehicleById getVehicleById = new GetVehicleById();
        getVehicleById.setVehicleId(request.getVehicle().getId());

        System.err.println(request.getCatalogue());
        System.err.println(request.getLocation());
        System.err.println(request.getPricelist());
        System.err.println(request.getVehicle());

        System.err.println("---------------- POZIVANJE KONTROLERA -------------------");

        // Pozvati location controller

        Location location = restTemplate.exchange("http://location/location",
                HttpMethod.POST, new HttpEntity<Location>(request.getLocation().toModel(request.getLocation())), new ParameterizedTypeReference<Location>() {}).getBody();

        System.err.println(location);
        request.getVehicle().setLocationId(location.getId());
        System.err.println("---------------- LOCATION PROSAO -------------------");

        // Pozvati catalogue controller

        VehicleFuelType vehicleFuelType = restTemplate.exchange("http://catalogue/catalogue/vehicleFuelType/createReturnObject",
                HttpMethod.POST, new HttpEntity<VehicleFuelType>(request.getCatalogue().getVehicleFuelType().toModel(request.getCatalogue().getVehicleFuelType())), new ParameterizedTypeReference<VehicleFuelType>() {}).getBody();
        request.getVehicle().setFuelTypeId(vehicleFuelType.getId());
        System.err.println(vehicleFuelType);

        VehicleMake vehicleMake = restTemplate.exchange("http://catalogue/catalogue/vehicleMake/createReturnObject",
                HttpMethod.POST, new HttpEntity<VehicleMake>(request.getCatalogue().getVehicleMake().toModel(request.getCatalogue().getVehicleMake())), new ParameterizedTypeReference<VehicleMake>() {}).getBody();
        request.getVehicle().setMakeId(vehicleMake.getId());
        System.err.println(vehicleMake);

        VehicleModel vehicleModelReq = request.getCatalogue().getVehicleModel().toModel(request.getCatalogue().getVehicleModel());
        vehicleModelReq.setVehicleMake(vehicleMake);
        VehicleModel vehicleModel = restTemplate.exchange("http://catalogue/catalogue/vehicleModel/createReturnObject",
                HttpMethod.POST, new HttpEntity<VehicleModel>(vehicleModelReq), new ParameterizedTypeReference<VehicleModel>() {}).getBody();
        request.getVehicle().setModelId(vehicleModel.getId());
        System.err.println(vehicleModel);

        VehicleTransmission vehicleTransmission = restTemplate.exchange("http://catalogue/catalogue/vehicleTransmission/createReturnObject",
                HttpMethod.POST, new HttpEntity<VehicleTransmission>(request.getCatalogue().getVehicleTransmission().toModel(request.getCatalogue().getVehicleTransmission())), new ParameterizedTypeReference<VehicleTransmission>() {}).getBody();
        request.getVehicle().setTransmissionId(vehicleTransmission.getId());
        System.err.println(vehicleTransmission);

        VehicleStyle vehicleStyle = restTemplate.exchange("http://catalogue/catalogue/vehicleStyle/createReturnObject",
                HttpMethod.POST, new HttpEntity<VehicleStyle>(request.getCatalogue().getVehicleStyle().toModel(request.getCatalogue().getVehicleStyle())), new ParameterizedTypeReference<VehicleStyle>() {}).getBody();
        request.getVehicle().setStyleId(vehicleStyle.getId());
        System.err.println(vehicleStyle);

        System.err.println("---------------- CATALOGUE PROSAO -------------------");

        // Pozvati vehicle service

        Vehicle vehicle = vehicleService.addAgentVehicle(request.getVehicle().toModel(request.getVehicle()));

        System.err.println(vehicle);
        System.err.println("---------------- VEHICLE PROSAO -------------------");

        // Pozvati pricelist controller
        // --- pretvoriti listu u model

        List<com.example.vehicle.dto.pricelist.Pricelist> pricelistsModel = new ArrayList<>();

        for(Pricelist pricelist : request.getPricelist()){

            System.err.println(pricelist.getStartDate());

            pricelist.setVehicleId(vehicle.getId());

            System.err.println(pricelist.getVehicleId());

            com.example.vehicle.dto.pricelist.Pricelist pricelist1 = pricelist.toModel(pricelist);
            pricelist1.setId(null);
            pricelist1.getVehicleDiscount().setId(null);
            pricelistsModel.add(pricelist1);
        }

        System.err.println(vehicle.getStartDate());
        System.err.println(vehicle.getStartDate());

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://pricelist/pricelist")
                .queryParam("startDate", vehicle.getStartDate().toString())
                .queryParam("endDate", vehicle.getEndDate().toString());

        Notification notification = restTemplate.exchange(builder.toUriString(),
                HttpMethod.PUT, new HttpEntity<List<com.example.vehicle.dto.pricelist.Pricelist>>(pricelistsModel), new ParameterizedTypeReference<Notification>() {}).getBody();

        System.err.println(notification);

        System.err.println("---------------- PRICELIST PROSAO -------------------");

        return getVehicleById;
    }
}
