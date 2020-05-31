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

    public List<VehicleMainViewDTO> getAllVehicleMainViewDTO(List<Vehicle> vehicleList, List<VehicleMake> vehicleMakeList, List<Pricelist> pricelist, List<VehicleModel> vehicleModelList, List<OwnerDTO> ownerList) {
        List<VehicleMainViewDTO> listVMV = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            listVMV.add(vehicleToVehicleMainViewDTO(vehicle, vehicleMakeList, pricelist, vehicleModelList, ownerList));
        }
        return listVMV;
    }

    public VehicleMainViewDTO vehicleToVehicleMainViewDTO(Vehicle vehicle, List<VehicleMake> vehicleMakeList, List<Pricelist> pricelist, List<VehicleModel> vehicleModelList, List<OwnerDTO> ownerList) {
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

    public String getOwner(List<OwnerDTO> list, Long vehicleOwnerId) {
        for (OwnerDTO owner : list) {
            if(vehicleOwnerId.equals(owner.getId())) {
                return owner.getUsername();
            }
        }
        return null;
    }
}
