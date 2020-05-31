package com.example.pricelist.service;

import com.example.pricelist.model.Notification;
import com.example.pricelist.model.Pricelist;
import com.example.pricelist.repository.PricelistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class PricelistService {

    @Autowired
    private PricelistRepository pricelistRepository;

    public List<Pricelist> getAll() { return pricelistRepository.findAll(); }

    public List<Pricelist> getAllByVehicle(Long vehicleId) {
        return pricelistRepository.findByVehicleId(vehicleId);
    }

    public Notification createPricelist(Pricelist pricelist) {
        Notification notification = new Notification("Failed to create pricelist.");
        try{
            if (dateRangeExists(pricelist)){
                notification.setText("Already defined price for requested date. If you want to create pricelist item please first delete all items which have requested date range.");
                return notification;
            }
            if (dateRangeOutdated(pricelist)){
                notification.setText("Date range outdated.");
                return notification;
            }
            if (dateRangeInvalid(pricelist)){
                notification.setText("Date range invalid.");
                return notification;
            }
            pricelistRepository.save(pricelist);
            notification.setText("Created pricelist for requested vehicle.");
            return notification;
        }
        catch(Exception e){

        }
        return notification;
    }

    public Notification updatePricelist(Pricelist pricelist) {
        Notification notification = new Notification("Failed to update pricelist.");
        try{
            if (dateRangeExists(pricelist)){
                notification.setText("Already defined price for requested date. If you want to update pricelist item please first delete all items which have requested date range.");
                return notification;
            }
            if (dateRangeOutdated(pricelist)){
                notification.setText("Date range outdated.");
                return notification;
            }
            if (dateRangeInvalid(pricelist)){
                notification.setText("Date range invalid.");
                return notification;
            }
            pricelistRepository.save(pricelist);
            notification.setText("Updated pricelist for requested vehicle.");
            return notification;
        }
        catch(Exception e){

        }
        return notification;
    }

    public Notification deletePricelist(Long pricelistId) {
        Notification notification = new Notification("Failed to delete pricelist.");
        try{
            if (!pricelistRepository.findById(pricelistId).isPresent()){
                notification.setText("Pricelist does not exist.");
                return notification;
            }
            Long vehicleId = pricelistRepository.findById(pricelistId).get().getVehicleId();
            if (pricelistRepository.findByVehicleId(vehicleId).size() == 1){
                notification.setText("Vehicle must have at least one pricelist defined.");
                return notification;
            }
            pricelistRepository.deleteById(pricelistId);
            notification.setText("Pricelist deleted.");
            return notification;
        }
        catch(Exception e){

        }
        return notification;
    }

    public boolean dateRangeExists(Pricelist pricelist){
        List<Pricelist> pricelists = getAllByVehicle(pricelist.getVehicleId());

        for (Pricelist p : pricelists){
            if ((pricelist.getStartDate().isAfter(p.getStartDate()) && pricelist.getStartDate().isBefore(p.getEndDate()))
            || (pricelist.getEndDate().isAfter(p.getStartDate()) && pricelist.getEndDate().isBefore(p.getEndDate()))
            || (pricelist.getStartDate().isBefore(p.getStartDate()) && pricelist.getEndDate().isAfter(p.getEndDate()))){
                return true;
            }
        }
        return false;
    }

    public boolean dateRangeOutdated(Pricelist pricelist){
        if (pricelist.getStartDate().isBefore(LocalDate.now())){
            return true;
        }
        return false;
    }

    public boolean dateRangeInvalid(Pricelist pricelist){
        if (pricelist.getStartDate().isAfter(pricelist.getEndDate())){
            return true;
        }
        return false;
    }
}
