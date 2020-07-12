package com.example.vehicle.service;

import com.example.vehicle.model.Notification;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.model.VehicleImage;
import com.example.vehicle.repository.VehicleImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class VehicleImageService {

    @Autowired
    VehicleImageRepository imageRepository;

    public VehicleImage upload(MultipartFile file) throws IOException{
        VehicleImage img = null;
        Random random = new Random();
        Long imageName = random.nextLong();
        while (imageRepository.findByName(Math.abs(imageName) + ".jpg").isPresent()) {
            imageName = random.nextLong();
        }
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        img = new VehicleImage(Math.abs(imageName) + ".jpg", file.getContentType(),
                compressBytes(file.getBytes()));
        imageRepository.save(img);


        return img;

    }

    public VehicleImage get(String imageName) throws IOException{
        try{
            if (imageRepository.findByName(imageName).isPresent()){
                Optional<VehicleImage> retrievedImage = imageRepository.findByName(imageName);
                VehicleImage img = new VehicleImage(retrievedImage.get().getName(), retrievedImage.get().getType(),
                        decompressBytes(retrievedImage.get().getPicByte()));
                return img;
            }
            else{
                return null;
            }

        }
        catch (Exception e){

        }
        return null;

    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

    public VehicleImage save(VehicleImage vehicleImage){
        return imageRepository.save(vehicleImage);
    }
}
