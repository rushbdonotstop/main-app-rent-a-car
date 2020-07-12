package com.example.pricelist.service;

import com.example.pricelist.dto.MinAndMaxPricesDTO;
import com.example.pricelist.model.Notification;
import com.example.pricelist.model.Pricelist;
import com.example.pricelist.model.VehicleDiscount;
import com.example.pricelist.repository.PricelistRepository;
import com.example.pricelist.repository.VehicleDiscountRepository;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
            System.out.println(pricelistList);
        }
        catch(Exception e){

        }
        return pricelistList;
    }

    @Transactional
    public Notification savePricelists(List<Pricelist> pricelists, LocalDateTime startDate, LocalDateTime endDate){
        Notification notification = new Notification("Failed to create pricelists.");
        try{
            if (validatePricelistsDate(pricelists, startDate, endDate) == null){
                notification.setText("Pricelists date invalid");
                return notification;
            }

            boolean allGood = true;

            for(Pricelist pricelist : pricelists){
               if(invalidPricelistsFields(pricelist)){
                   System.out.println("InvalidPriceListFields");
                    allGood = false;
               }
            }

            if (allGood){
                for(Pricelist pricelist : pricelists){
                    pricelistRepository.save(pricelist);
                    if(pricelist.getVehicleDiscount() != null){
                        vehicleDiscountRepository.save(pricelist.getVehicleDiscount());
                    }
                }
                notification.setText("Updated pricelists!");
            }
        }
        catch(Exception e){

        }
        return notification;
    }

    public boolean invalidPricelistsFields(Pricelist pricelist) {
        try{
            if (priceInvalid(pricelist)){
                return true;
            }

            if (pricelist.getVehicleDiscount() != null){
                if (discountInvalid(pricelist.getVehicleDiscount())){
                    return true;
                }
            }

            return false;
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

    // validacija pricelists datuma u odnosu na datum vozila

    public List<Pricelist> validatePricelistsDate(List<Pricelist> pricelists, LocalDateTime startDate, LocalDateTime endDate) {
        try{
            if(pricelists.size() == 0){
                System.out.println("Pricelist size 0");
                return null;
            }

            LocalDate sd = new LocalDate(startDate.getYear(), startDate.getMonthValue(), startDate.getDayOfMonth());
            LocalDate ed = new LocalDate(endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfMonth());

            int carDays = Days.daysBetween(sd, ed).getDays();
            int pricelistDays = 0;

            if(pricelists.size() == 1){
                sd = new LocalDate(pricelists.get(0).getStartDate().getYear(), pricelists.get(0).getStartDate().getMonthValue(), pricelists.get(0).getStartDate().getDayOfMonth());
                ed = new LocalDate(pricelists.get(0).getEndDate().getYear(), pricelists.get(0).getEndDate().getMonthValue(), pricelists.get(0).getEndDate().getDayOfMonth());
                pricelistDays += Days.daysBetween(sd, ed).getDays();
                if(dateRangeOutdated(pricelists.get(0)) || dateRangeInvalid(pricelists.get(0))
                        || (!pricelists.get(0).getStartDate().equals(startDate) && !pricelists.get(0).getEndDate().equals(endDate))
                        || carDays != pricelistDays){
                    System.err.println("Pricelist size == 1 == null");
                    System.err.println(pricelists.get(0).getStartDate());
                    System.err.println(pricelists.get(0).getEndDate());
                    System.err.println(carDays + " ==? " + pricelistDays);
                    return null;
                }
                return pricelists;
            }

            sd = new LocalDate(pricelists.get(0).getStartDate().getYear(), pricelists.get(0).getStartDate().getMonthValue(), pricelists.get(0).getStartDate().getDayOfMonth());
            ed = new LocalDate(pricelists.get(0).getEndDate().getYear(), pricelists.get(0).getEndDate().getMonthValue(), pricelists.get(0).getEndDate().getDayOfMonth());
            pricelistDays += Days.daysBetween(sd, ed).getDays();

            for (int i = 1 ; i < pricelists.size(); i++) {
                sd = new LocalDate(pricelists.get(i).getStartDate().getYear(), pricelists.get(i).getStartDate().getMonthValue(), pricelists.get(i).getStartDate().getDayOfMonth());
                ed = new LocalDate(pricelists.get(i).getEndDate().getYear(), pricelists.get(i).getEndDate().getMonthValue(), pricelists.get(i).getEndDate().getDayOfMonth());
                pricelistDays += Days.daysBetween(sd, ed).getDays() + 1;
            }

            for(int i = 0 ; i < pricelists.size(); i++) {
                for (int j = 1; j < pricelists.size(); j++) {
                    if (i != j) {
                        if (dateRangeOutdated(pricelists.get(i)) || dateRangeInvalid(pricelists.get(i)) || dateRangeOverlap(pricelists.get(i), pricelists.get(j))
                                || dateRangeNotCovering(pricelists.get(i), startDate, endDate)) {
                            return null;
                        }
                    }
                }
            }

            System.out.println(carDays);
            System.out.println(pricelistDays);

            if (carDays != pricelistDays){
                return null;
            }

            return pricelists;
        }
        catch(Exception e){
            System.out.println("Exception in function validatePricelistsDate " + e.getLocalizedMessage());
        }
        return null;
    }

    public boolean dateRangeNotCovering(Pricelist pricelist, LocalDateTime startDate, LocalDateTime endDate){
        if((startDate.toLocalDate().isBefore(pricelist.getStartDate().toLocalDate()) && endDate.toLocalDate().isAfter(pricelist.getEndDate().toLocalDate()))
        || (startDate.toLocalDate().isBefore(pricelist.getStartDate().toLocalDate()) && endDate.toLocalDate().equals(pricelist.getEndDate().toLocalDate()))
        || (startDate.toLocalDate().equals(pricelist.getStartDate().toLocalDate()) && endDate.toLocalDate().isAfter(pricelist.getEndDate().toLocalDate()))
        || (startDate.toLocalDate().equals(pricelist.getStartDate().toLocalDate()) && endDate.toLocalDate().equals(pricelist.getEndDate().toLocalDate()))){
            return false;
        }
        System.out.println("Not covering");
        return true;
    }

    public boolean dateRangeOverlap(Pricelist pricelist1, Pricelist pricelist2){
        if ((pricelist1.getStartDate().toLocalDate().isBefore(pricelist2.getEndDate().toLocalDate()) && pricelist2.getStartDate().toLocalDate().isBefore(pricelist1.getEndDate().toLocalDate()))
            || pricelist1.getStartDate().toLocalDate().equals(pricelist2.getEndDate().toLocalDate())
            || pricelist1.getEndDate().toLocalDate().equals(pricelist2.getStartDate().toLocalDate())
            || pricelist1.getStartDate().toLocalDate().equals(pricelist2.getStartDate().toLocalDate())
            || pricelist1.getEndDate().toLocalDate().equals(pricelist2.getEndDate().toLocalDate())){
            System.out.println("Overlap");
            return true;
        }
        return false;
    }

    public boolean dateRangeOutdated(Pricelist pricelist){
        if (pricelist.getStartDate().toLocalDate().isBefore(java.time.LocalDate.now()) && !pricelist.getStartDate().toLocalDate().equals(java.time.LocalDate.now())){
            System.out.println("Outdated");
            return true;
        }
        return false;
    }

    public boolean dateRangeInvalid(Pricelist pricelist){
        if (pricelist.getStartDate().toLocalDate().isAfter(pricelist.getEndDate().toLocalDate())){
            System.out.println("Invalid");
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
}
