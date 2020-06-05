package com.example.vehicle.controller;

import com.example.vehicle.model.Notification;
import com.example.vehicle.model.VehicleImage;
import com.example.vehicle.repository.VehicleImageRepository;
import com.example.vehicle.service.VehicleImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "vehicle/image")
public class ImageUploadController {

    @Autowired
    VehicleImageService vehicleImageService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleImage> uploadImage(@RequestParam (value = "imageFile") MultipartFile imageFile) throws IOException {
        VehicleImage img  = vehicleImageService.upload(imageFile);
        return new ResponseEntity<VehicleImage>(img, HttpStatus.OK);
    }

    @GetMapping(value = "/{imageName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleImage> getImage(@PathVariable("imageName") String imageName) throws IOException {

        VehicleImage img = vehicleImageService.get(imageName);

        return new ResponseEntity<VehicleImage>(img, HttpStatus.OK);
    }

}
