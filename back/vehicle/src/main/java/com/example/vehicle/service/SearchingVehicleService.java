package com.example.vehicle.service;

import com.example.vehicle.dto.*;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class SearchingVehicleService {

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

        return vmvDTO;
    }

    public float getPrice(List<Pricelist> list, Long vehicleId) {
        List<Pricelist> newList = new ArrayList();
        for (Pricelist pricelist : list) {
            if (vehicleId.equals(pricelist.getVehicleId())) {
                newList.add(pricelist);
            }
            if (newList.size()==1) {
                return newList.get(0).getPrice();
            } else {
                Collections.sort(newList);
                return newList.get(0).getPrice();
            }
        }
        return 0;
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

    public List<Vehicle> getVehiclesByMake(List<Vehicle> list, Long id) {
        if (id == 0) {
            return list;
        }

        List<Vehicle> vehicleList = new ArrayList<>();

        for(Vehicle vehicle : list) {
            if (vehicle.getMakeId().equals(id)) {
                vehicleList.add(vehicle);
            }
        }
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

        if(state.equals("") && city.equals("")) {
            newLocations = locationList;
        }
        if(!state.equals("") && city.equals("")) {
            for(Location location : locationList) {
                if (location.getState().equals(state)) {
                    newLocations.add(location);
                }
            }
        }
        if (state.equals("") && !city.equals("")) {
            for(Location location : locationList) {
                if (location.getCity().equals(city)) {
                    newLocations.add(location);
                }
            }
        }
        if(!state.equals("") && !city.equals("")) {
            for(Location location : locationList) {
                if (location.getState().equals(state) && location.getCity().equals(city)) {
                    newLocations.add(location);
                }
            }
        }
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

    public List<VehicleMainViewDTO> parameterizedSearch(List<Vehicle> vehicleList, List<Location> locationList, List<VehicleMake> vehicleMakeList, List<Pricelist> pricelistList, List<VehicleModel> vehicleModelList, List<UserDTO> ownerList, Long makeId, Long modelId, Long styleId, Long fuelId, Long transmissionId, int maxMileage, int mileageLimit, boolean collisionProtection, int childrenSeats, String state, String city, float priceLowerLimit, float priceUpperLimit) {

        List<Vehicle> newList = getVehiclesByMake(vehicleList, makeId);
        newList = getVehiclesByModel(newList, modelId);
        newList = getVehiclesByStyle(newList, styleId);
        newList = getVehiclesByFuel(newList, fuelId);
        newList = getVehiclesByTransmission(newList, transmissionId);
        newList = getVehiclesByMaxMileage(newList, maxMileage);
        newList = getVehiclesByMileageLimit(newList, mileageLimit);
        newList = getVehiclesByCollisionDamage(newList, collisionProtection);
        newList = getVehiclesByChildrenSeats(newList, childrenSeats);
        newList = getVehiclesByLocation(newList, locationList, state, city);
        newList = getVehiclesByPrice(newList, pricelistList, priceLowerLimit, priceUpperLimit);

        List<VehicleMainViewDTO> dtoList = getAllVehicleMainViewDTO(newList, vehicleMakeList, pricelistList, vehicleModelList, ownerList);
        return dtoList;
    }


}
