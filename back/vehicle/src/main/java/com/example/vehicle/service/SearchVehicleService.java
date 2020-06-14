package com.example.vehicle.service;

import com.example.vehicle.dto.*;
import com.example.vehicle.dto.catalogue.VehicleMake;
import com.example.vehicle.dto.catalogue.VehicleModel;
import com.example.vehicle.dto.location.Location;
import com.example.vehicle.dto.pricelist.Pricelist;
import com.example.vehicle.dto.request.RequestForVehicleDTO;
import com.example.vehicle.dto.request.Status;
import com.example.vehicle.dto.user.UserDTO;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.transform.sax.SAXSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class SearchVehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Vehicle findOneById(Long Id) {
        return vehicleRepository.findOneById(Id);
    }

    public void removeById(Long Id) {
         vehicleRepository.removeById(Id);
    }

    public List<Vehicle> findByCollisionProtection(boolean collisionProtection) {
        return vehicleRepository.findByCollisionProtection(collisionProtection);
    }

    public List<Vehicle> findByChildrenSeats(int childrenSeats) {
        return vehicleRepository.findByChildrenSeats(childrenSeats);
    }

    public List<Vehicle> findByStartDate(Date startDate) {
        return vehicleRepository.findByStartDate(startDate);
    }

    public List<Vehicle> findByEndDate(Date endDate) {
        return vehicleRepository.findByEndDate(endDate);
    }

    public List<Vehicle> findByVehicleFuelType(Long Id) {
        return vehicleRepository.findByFuelTypeId(Id);
    }

    public List<Vehicle> findByVehicleMake(Long Id) {
        return vehicleRepository.findByMakeId(Id);
    }

    public List<Vehicle> findByVehicleModel(Long Id) {
        return vehicleRepository.findByModelId(Id);
    }

    public List<Vehicle> findByVehicleStyle(Long Id) {
        return vehicleRepository.findByStyleId(Id);
    }

    public List<Vehicle> findByVehicleTransmission(Long Id) {
        return vehicleRepository.findByStyleId(Id);
    }

    public List<VehicleMainViewDTO> getAllVehicleMainViewDTO(List<Vehicle> vehicleList, List<VehicleMake> vehicleMakeList, List<Pricelist> pricelist, List<VehicleModel> vehicleModelList, List<UserDTO> ownerList) {
        List<VehicleMainViewDTO> listVMV = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            listVMV.add(vehicleToVehicleMainViewDTO(vehicle, vehicleMakeList, pricelist, vehicleModelList, ownerList));
        }
        return listVMV;
    }

    public VehicleMainViewDTO vehicleToVehicleMainViewDTO(Vehicle vehicle, List<VehicleMake> vehicleMakeList, List<Pricelist> pricelist, List<VehicleModel> vehicleModelList, List<UserDTO> ownerList) {
        VehicleMainViewDTO vmvDTO = new VehicleMainViewDTO();
        vmvDTO.setId(vehicle.getId());
        vmvDTO.setMake(getVehicleMake(vehicleMakeList, vehicle.getMakeId()));
        vmvDTO.setModel(getVehicleModel(vehicleModelList, vehicle.getModelId()));
        vmvDTO.setPrice(getPrice(pricelist, vehicle.getId()));
        vmvDTO.setOwnerUsername(getOwner(ownerList, vehicle.getUserId()));
        vmvDTO.setOwnerId(getOwnerId(ownerList, vehicle.getUserId()));
        vmvDTO.setMileage(vehicle.getMileage());

        return vmvDTO;
    }

    public float getPrice(List<Pricelist> list, Long vehicleId) {
        List<Pricelist> newList = new ArrayList();
        for (Pricelist pricelist : list) {
            if (vehicleId.equals(pricelist.getVehicleId())) {
                newList.add(pricelist);
            }
        }
        if (newList.size() == 0) {
            return 0;
        }
        if (newList.size()==1) {
            return newList.get(0).getPrice();
        } else {
            Collections.sort(newList);
            return newList.get(0).getPrice();
        }
    }

    public String getVehicleMake(List<VehicleMake> list, Long vehicleMakeId) {
        for (VehicleMake vehicleMake : list) {
            if (vehicleMakeId.equals(vehicleMake.getId())) {
                return vehicleMake.getValue();
            }
        }
        return null;
    }

    public String getVehicleModel(List<VehicleModel> list, Long vehicleModelId) {
        for (VehicleModel vehicleModel : list) {
            if(vehicleModelId.equals(vehicleModel.getId())) {
                return vehicleModel.getValue();
            }
        }
        return null;
    }

    public String getOwner(List<UserDTO> list, Long vehicleOwnerId) {
        for (UserDTO owner : list) {
            if(vehicleOwnerId.equals(owner.getId())) {
                return owner.getUsername();
            }
        }
        return null;
    }

    public Long getOwnerId(List<UserDTO> list, Long vehicleOwnerId) {
        for (UserDTO owner : list) {
            if(vehicleOwnerId.equals(owner.getId())) {
                return owner.getId();
            }
        }
        return null;
    }

    public List<Vehicle> getVehiclesByMake(List<Vehicle> list, Long id) {
        if (id == 0) {
            return list;
        }

        List<Vehicle> vehicleList = new ArrayList<>();

        for(Vehicle vehicle : list) {
            System.out.println("VREDNOST ID VOZILA JE: " + vehicle.getMakeId());
            System.out.println("VREDNOST POSLATOG ID JE: " + id);
            if (vehicle.getMakeId() == id) {
                System.out.println("USAO U IF");
                vehicleList.add(vehicle);
            }
        }
        System.out.println("SIZE JE: " + vehicleList.size());
        return vehicleList;
    }

    public List<Vehicle> getVehiclesByModel(List<Vehicle> list, Long id) {
        if ( id== 0) {
            return list;
        }

        List<Vehicle> vehicleList = new ArrayList<>();
        for(Vehicle vehicle : list) {
            if (vehicle.getModelId().equals(id)) {
                vehicleList.add(vehicle);
            }
        }
        return vehicleList;
    }

    public List<Vehicle> getVehiclesByStyle(List<Vehicle> list, Long id) {
        if (id== 0) {
            return list;
        }

        List<Vehicle> vehicleList = new ArrayList<>();
        for(Vehicle vehicle : list) {
            if (vehicle.getStyleId().equals(id)) {
                vehicleList.add(vehicle);
            }
        }
        return vehicleList;
    }

    public List<Vehicle> getVehiclesByFuel(List<Vehicle> list, Long id) {
        if (id== 0) {
            return list;
        }

        List<Vehicle> vehicleList = new ArrayList<>();
        for(Vehicle vehicle : list) {
            if (vehicle.getFuelTypeId().equals(id)) {
                vehicleList.add(vehicle);
            }
        }
        return vehicleList;
    }

    public List<Vehicle> getVehiclesByTransmission(List<Vehicle> list, Long id) {
        if (id== 0) {
            return list;
        }

        List<Vehicle> vehicleList = new ArrayList<>();
        for(Vehicle vehicle : list) {
            if (vehicle.getTransmissionId().equals(id)) {
                vehicleList.add(vehicle);
            }
        }
        return vehicleList;
    }

    public List<Vehicle> getVehiclesByMaxMileage(List<Vehicle> list, int maxMileage) {
        if (maxMileage == 0) {
            return list;
        }
        List<Vehicle> vehicleList = new ArrayList<>();
        for(Vehicle vehicle : list) {
            if(vehicle.getMileage() <= maxMileage) {
                vehicleList.add(vehicle);
            }
        }
        return vehicleList;
    }

    public List<Vehicle> getVehiclesByMileageLimit(List<Vehicle> list, int mileageLimit) {
        if(mileageLimit == 0) {
            return list;
        }
        List<Vehicle> vehicleList = new ArrayList<>();
        for(Vehicle vehicle : list) {
            if(vehicle.getMileageLimit() >= mileageLimit) {
                System.out.println(vehicle.getMileageLimit() + " JE ");
                System.out.println("VECE OD ");
                System.out.println(mileageLimit);
                vehicleList.add(vehicle);
            }
        }
        return vehicleList;
    }

    public List<Vehicle> getVehiclesByCollisionDamage(List<Vehicle> list, boolean collisionProtection) {
        List<Vehicle> vehicleList = new ArrayList<>();

        if (collisionProtection == true) {
            for (Vehicle vehicle : list) {
                if (vehicle.getCollisionProtection() == true) {
                    vehicleList.add(vehicle);
                }
            }
            return vehicleList;
        } else {
            for(Vehicle vehicle : list) {
                if (vehicle.getCollisionProtection() == false) {
                    vehicleList.add(vehicle);
                }
            }
            return vehicleList;
        }
    }

    public List<Vehicle> getVehiclesByChildrenSeats (List<Vehicle> list, int childrenSeats) {
        List<Vehicle> vehicleList = new ArrayList<>();

        if (childrenSeats == -1) {
            return list;
        }

        if (childrenSeats == 0) {
            for (Vehicle vehicle : list) {
                if (vehicle.getChildrenSeats() == 0) {
                    vehicleList.add(vehicle);
                }
            }
            return vehicleList;
        } else {
            for(Vehicle vehicle : list) {
                if (vehicle.getChildrenSeats() >= childrenSeats) {
                    vehicleList.add(vehicle);
                }
            }
            return vehicleList;
        }
    }

    public List<Vehicle> getVehiclesByLocation(List<Vehicle> vehicleList, List<Location> locationList, String state, String city) {
        List<Location> newLocations = new ArrayList<>();
        List<Vehicle> newVehicleList = new ArrayList<>();
        System.out.println("DOBIJEN STATE JE: " + state);
        System.out.println("DOBIJEN CITY JE: " + city);
        if(state.equals("") && city.equals("")) {
            newLocations = locationList;
        }
        if(!state.equals("") && city.equals("")) {
            System.out.println("USAO 1");
            for(Location location : locationList) {
                if (location.getState().getValue().equals(state)) {
                    newLocations.add(location);
                }
            }
        }
        if (state.equals("") && !city.equals("")) {
            System.out.println("USAO 2");

            for(Location location : locationList) {
                if (location.getCity().getValue().equals(city)) {
                    newLocations.add(location);
                }
            }
        }
        if(!state.equals("") && !city.equals("")) {
            System.out.println("USAO 3");

            for(Location location : locationList) {
                if (location.getState().getValue().equals(state) && location.getCity().getValue().equals(city)) {
                    newLocations.add(location);
                }
            }
        }
        System.out.println("VELICINA LOKACIJA LISTE JE: " + newLocations.size() );
        for(Location location : newLocations) {
            for(Vehicle vehicle : vehicleList) {
                if(vehicle.getLocationId().equals(location.getId())) {
                    newVehicleList.add(vehicle);
                }
            }
        }
        return newVehicleList;
    }

    public List<Vehicle> getVehiclesByPrice(List<Vehicle> vehicleList, List<Pricelist> pricelistList, float priceLowerLimit, float priceUpperLimit) {
        List<Vehicle> newVehicleList = new ArrayList<>();

        for(Vehicle vehicle: vehicleList) {
            for (Pricelist pricelist : pricelistList) {
                if (pricelist.getVehicleId().equals(vehicle.getId())) {
                    if(pricelist.getPrice() >= priceLowerLimit && pricelist.getPrice() <= priceUpperLimit) {
                        newVehicleList.add(vehicle);
                        break;
                    }
                }
            }
        }
        return newVehicleList;

    }

    public List<Vehicle> getVehiclesByDate(List<Vehicle> vehicleList, List<RequestForVehicleDTO> requestList, LocalDateTime startDate, LocalDateTime endDate) {
        List<Vehicle> newVehicleList = new ArrayList<>();
        if (startDate == null || endDate == null) {
            return vehicleList;
        }
        for(Vehicle vehicle : vehicleList) {
            if (vehicle.getStartDate().isBefore(startDate) && vehicle.getEndDate().isAfter(endDate)) {
                newVehicleList.add(vehicle);
            }
        }

        if (newVehicleList.size() == 0) {
            return newVehicleList;
        }

        List<Vehicle> tempList = new ArrayList<>();

        for (Vehicle vehicle : newVehicleList){
            tempList.add(vehicle);
        }

        for(Vehicle vehicle : newVehicleList) {
            for (RequestForVehicleDTO request : requestList) {
                if (request.getVehicleId().equals(vehicle.getId())) {
                    if (((request.getStartDate().isBefore(startDate) && request.getEndDate().isAfter(endDate)) || (request.getStartDate().isBefore(startDate) && request.getEndDate().isAfter(startDate) && request.getEndDate().isBefore(endDate) || (request.getStartDate().isAfter(startDate) && request.getStartDate().isBefore(endDate) && request.getEndDate().isAfter(endDate)) || (request.getStartDate().isAfter(startDate) && request.getStartDate().isBefore(endDate) && request.getEndDate().isBefore(endDate) && request.getEndDate().isAfter(startDate)) )) && (request.getStatus().equals(Status.RESERVED)|| request.getStatus().equals(Status.PAID))) {
                        tempList.remove(vehicle);
                        break;
                    }
                }
            }
        }

        return tempList;
    }

    public List<VehicleMainViewDTO> parameterizedSearch(List<Vehicle> vehicleList, List<RequestForVehicleDTO> requestsList,  List<Location> locationList, List<VehicleMake> vehicleMakeList, List<Pricelist> pricelistList, List<VehicleModel> vehicleModelList, List<UserDTO> ownerList, Long makeId, Long modelId, Long styleId, Long fuelId, Long transmissionId, int maxMileage, int mileageLimit, boolean collisionProtection, int childrenSeats, String state, String city, float priceLowerLimit, float priceUpperLimit, LocalDateTime startDate, LocalDateTime endDate) {

        List<Vehicle> newList = getVehiclesByMake(vehicleList, makeId);
        System.out.println("Velicina je: " +newList.size());
        newList = getVehiclesByModel(newList, modelId);
        System.out.println("Velicina je: " +newList.size());
        newList = getVehiclesByStyle(newList, styleId);
        System.out.println("Velicina je: " +newList.size());
        newList = getVehiclesByFuel(newList, fuelId);
        System.out.println("Velicina je: " +newList.size());
        newList = getVehiclesByTransmission(newList, transmissionId);
        System.out.println("Velicina je: " +newList.size());
        newList = getVehiclesByMaxMileage(newList, maxMileage);
        System.out.println("Velicina je: " +newList.size());
        newList = getVehiclesByMileageLimit(newList, mileageLimit);
        System.out.println("Velicina je: " +newList.size());
        newList = getVehiclesByCollisionDamage(newList, collisionProtection);
        System.out.println("Velicina je: " +newList.size());
        newList = getVehiclesByChildrenSeats(newList, childrenSeats);
        System.out.println("Velicina je: " +newList.size());
        newList = getVehiclesByLocation(newList, locationList, state, city);
        System.out.println("Velicina je: " +newList.size());
        newList = getVehiclesByDate(newList, requestsList, startDate, endDate);
        System.out.println("Velicina je: " +newList.size());
        newList = getVehiclesByPrice(newList, pricelistList, priceLowerLimit, priceUpperLimit);
        System.out.println("Velicina je: " +newList.size());

        List<VehicleMainViewDTO> dtoList = getAllVehicleMainViewDTO(newList, vehicleMakeList, pricelistList, vehicleModelList, ownerList);
        return dtoList;
    }

    public List<VehicleMainViewDTO> getNotBlocked(List<VehicleMainViewDTO> vehicles, List<UserDTO> users) {
        List<VehicleMainViewDTO> newList = new ArrayList<>();

        for (VehicleMainViewDTO vehicle : vehicles) {
            for (UserDTO user : users) {
                if (vehicle.getOwnerId().equals(user.getId())) {
                    newList.add(vehicle);
                    break;
                }
            }
        }
        return newList;
    }


}
