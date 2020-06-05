package com.example.pricelist.service;

import com.example.pricelist.dto.MinAndMaxPricesDTO;
import com.example.pricelist.model.Notification;
import com.example.pricelist.model.Pricelist;
import com.example.pricelist.model.VehicleDiscount;
import com.example.pricelist.repository.PricelistRepository;
import com.example.pricelist.repository.VehicleDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class PricelistService {

    @Autowired
    private PricelistRepository pricelistRepository;

    @Autowired
    private VehicleDiscountRepository vehicleDiscountRepository;

    public List<Pricelist> getAll() { return pricelistRepository.findAll(); }

    public List<Pricelist> getAllByVehicle(Long vehicleId) {
        List<Pricelist> pricelistList = null;
        try{
            pricelistList = pricelistRepository.findByVehicleId(vehicleId);
        }
        catch(Exception e){

        }
        return pricelistList;
    }

    public Notification createPricelists(List<Pricelist> pricelists, LocalDate startDate, LocalDate endDate){
        Notification notification = new Notification("Failed to create pricelists.");
        try{
            for(Pricelist pricelist : pricelists){
                for(Pricelist pricelistCheck : pricelists){
                    if (dateRangeOverlap(pricelist, pricelistCheck)){
                        notification.setText("Date ranges overlap.");
                        return notification;
                    }
                }
            }

            boolean allGood = false;

            for(Pricelist pricelist : pricelists){
               if(createPricelist(pricelist, startDate, endDate, "CREATE")){
                    allGood = true;
               }
            }

            if (allGood){
                for(Pricelist pricelist : pricelists){
                    pricelistRepository.save(pricelist);
                    vehicleDiscountRepository.save(pricelist.getVehicleDiscount());
                }
                notification.setText("Created pricelists!");
            }
        }
        catch(Exception e){

        }
        return notification;
    }

    public Notification updatePricelists(List<Pricelist> pricelists, LocalDate startDate, LocalDate endDate){
        Notification notification = new Notification("Failed to update pricelist.");
        try{
            for(Pricelist pricelist : pricelists){
                for(Pricelist pricelistCheck : pricelists){
                    if (dateRangeOverlap(pricelist, pricelistCheck)){
                        notification.setText("Date ranges overlap.");
                        return notification;
                    }
                }
            }

            boolean allGood = false;

            for(Pricelist pricelist : pricelists){
                if(createPricelist(pricelist, startDate, endDate, "UPDATE")){
                    allGood = true;
                }
            }

            if (allGood){
                for(Pricelist pricelist : pricelists){
                    pricelistRepository.save(pricelist);
                    vehicleDiscountRepository.save(pricelist.getVehicleDiscount());
                }
                notification.setText("Updated pricelists!");
            }
        }
        catch(Exception e){

        }
        return notification;
    }

    public boolean createPricelist(Pricelist pricelist, LocalDate startDate, LocalDate endDate, String operation) {
        try{
            if (priceInvalid(pricelist)){
                return false;
            }

            if (discountInvalid(pricelist.getVehicleDiscount())){
                return false;
            }

            if (dateRangeExists(pricelist, operation)){
                return false;
            }
            if (dateRangeOutdated(pricelist)){
                return false;
            }
            if (dateRangeInvalid(pricelist)){
                return false;
            }

            return true;
        }
        catch(Exception e){

        }
        return false;
    }

    public boolean updatePricelist(Pricelist pricelist, LocalDate startDate, LocalDate endDate, String operation) {
        try{
            if (!pricelistRepository.findById(pricelist.getId()).isPresent()){
                return false;
            }

            if (priceInvalid(pricelist)){
                return false;
            }

            if (discountInvalid(pricelist.getVehicleDiscount()) || !vehicleDiscountRepository.findById(pricelist.getVehicleDiscount().getId()).isPresent()){
                return false;
            }

            if (dateRangeExists(pricelist, operation) && pricelistRepository.findByVehicleId(pricelist.getVehicleId()).size() != 1){
                return false;
            }
            if (dateRangeOutdated(pricelist)){
                return false;
            }
            if (dateRangeInvalid(pricelist)){
                return false;
            }
            return true;
        }
        catch(Exception e){

        }
        return false;
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

    // Za uopsteno kreiranje kod postojeceg vozila

    public boolean dateRangeExists(Pricelist pricelist, String operation){
        List<Pricelist> pricelists = getAllByVehicle(pricelist.getVehicleId());

        for (Pricelist p : pricelists){
            if ((pricelist.getStartDate().isAfter(p.getStartDate()) && pricelist.getStartDate().isBefore(p.getEndDate()))
            || (pricelist.getEndDate().isAfter(p.getStartDate()) && pricelist.getEndDate().isBefore(p.getEndDate()))
            || (pricelist.getStartDate().isBefore(p.getStartDate()) && pricelist.getEndDate().isAfter(p.getEndDate()))){
                if (operation.equals("CREATE")){
                    if (pricelist.getStartDate().equals(p.getStartDate()) && pricelist.getEndDate().equals(p.getEndDate())){
                        return true;
                    }
                }
                return true;
            }
        }
        return false;
    }

    public boolean dateRangeOverlap(Pricelist pricelist1, Pricelist pricelist2){
        if ((pricelist1.getStartDate().isAfter(pricelist2.getStartDate()) && pricelist1.getStartDate().isBefore(pricelist2.getEndDate()))
                || (pricelist1.getEndDate().isAfter(pricelist2.getStartDate()) && pricelist1.getEndDate().isBefore(pricelist2.getEndDate()))
                || (pricelist1.getStartDate().isBefore(pricelist2.getStartDate()) && pricelist1.getEndDate().isAfter(pricelist2.getEndDate()))
                || pricelist1.getStartDate().equals(pricelist2.getStartDate())
                || pricelist1.getStartDate().equals(pricelist2.getEndDate())
                || pricelist1.getEndDate().equals(pricelist2.getStartDate())
                || pricelist1.getEndDate().equals(pricelist2.getEndDate())){
            return true;
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

    public boolean discountInvalid(VehicleDiscount vehicleDiscount){
        if(vehicleDiscount.getDiscount() < 1 || vehicleDiscount.getDiscount() > 100
        || vehicleDiscount.getNumDays() < 0){
            return true;
        }
        return false;
    }

    public boolean priceInvalid(Pricelist pricelist){
        if(pricelist.getPrice() < 0 || pricelist.getPriceByMile() < 0 || pricelist.getPriceCollision() < 0){
            return true;
        }
        return false;
    }

    // Za search slider

    public MinAndMaxPricesDTO getMinAndMax() {
        List<Pricelist> list = pricelistRepository.findAll();
        float min = list.get(0).getPrice();
        float max = list.get(0).getPrice();
        for (Pricelist pricelist : list) {
            if(pricelist.getPrice() > max) {
                max = pricelist.getPrice();
            }

            if (pricelist.getPrice() < min) {
                min = pricelist.getPrice();
            }
        }
        if (min == max) {
            min = 0;
        }
        MinAndMaxPricesDTO minAndMax = new MinAndMaxPricesDTO(min, max);

        return minAndMax;
    }

    // Prilikom kreiranja vozila

    public List<Pricelist> validatePricelists(List<Pricelist> pricelists, LocalDate startDate, LocalDate endDate) {
        try{
            for(Pricelist pricelist : pricelists){
                for (Pricelist pricelistCheck : pricelists){
                    if(dateRangeOutdated(pricelist) || dateRangeInvalid(pricelist) || dateRangeOverlap(pricelist, pricelistCheck)
                    || dateRangeNotCovering(pricelist, startDate, endDate)){
                        return null;
                    }
                }
            }
            return pricelists;
        }
        catch(Exception e){

        }
        return null;
    }

    public boolean dateRangeNotCovering(Pricelist pricelist, LocalDate startDate, LocalDate endDate){
        if(startDate.isBefore(pricelist.getStartDate()) && endDate.isAfter(pricelist.getEndDate())
        || (startDate.equals(pricelist.getStartDate()) && endDate.equals(pricelist.getEndDate()))){
            return false;
        }
        return true;
    }
}
