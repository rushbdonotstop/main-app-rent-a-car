package com.example.vehicle.service;

import com.example.vehicle.dto.VehicleMainViewDTO;
import com.example.vehicle.dto.catalogue.VehicleMakeDTO;
import com.example.vehicle.dto.catalogue.VehicleModelDTO;
import com.example.vehicle.dto.pricelist.PriceListDTO;
import com.example.vehicle.dto.user.OwnerDTO;
import com.example.vehicle.model.Vehicle;
import com.example.vehicle.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<VehicleMainViewDTO> getAllVehicleMainViewDTO(List<Vehicle> vehicleList, List<VehicleMakeDTO> vehicleMakeList, List<PriceListDTO> pricelist, List<VehicleModelDTO> vehicleModelList, List<OwnerDTO> ownerList) {
        List<VehicleMainViewDTO> listVMV = new ArrayList<>();
        for (Vehicle vehicle : vehicleList) {
            listVMV.add(vehicleToVehicleMainViewDTO(vehicle, vehicleMakeList, pricelist, vehicleModelList, ownerList));
        }
        return listVMV;
    }

    public VehicleMainViewDTO vehicleToVehicleMainViewDTO(Vehicle vehicle, List<VehicleMakeDTO> vehicleMakeList, List<PriceListDTO> pricelist, List<VehicleModelDTO> vehicleModelList, List<OwnerDTO> ownerList) {
        VehicleMainViewDTO vmvDTO = new VehicleMainViewDTO();
        vmvDTO.setId(vehicle.getId());
        vmvDTO.setMake(getVehicleMake(vehicleMakeList, vehicle.getMakeId()));
        vmvDTO.setModel(getVehicleModel(vehicleModelList, vehicle.getModelId()));
        vmvDTO.setPriceListDTO(getPricelist(pricelist, vehicle.getId()));
        vmvDTO.setOwnerDTO(getOwner(ownerList, vehicle.getUserId()));

        return vmvDTO;
    }

    public PriceListDTO getPricelist(List<PriceListDTO> list,Long vehicleId) {
        for (PriceListDTO pricelist : list) {
            if (vehicleId.equals(pricelist.getVehicleId())) {
                return pricelist;
            }

        }
        return null;
    }

    public VehicleMakeDTO getVehicleMake(List<VehicleMakeDTO> list, Long vehicleMakeId) {
        for (VehicleMakeDTO vehicleMake : list) {
            if (vehicleMakeId.equals(vehicleMake.getId())) {
                return vehicleMake;
            }
        }
        return null;
    }

    public VehicleModelDTO getVehicleModel(List<VehicleModelDTO> list, Long vehicleModelId) {
        for (VehicleModelDTO vehicleModel : list) {
            if(vehicleModelId.equals(vehicleModel.getId())) {
                return vehicleModel;
            }
        }
        return null;
    }

    public OwnerDTO getOwner(List<OwnerDTO> list, Long vehicleOwnerId) {
        for (OwnerDTO owner : list) {
            if(vehicleOwnerId.equals(owner.getId())) {
                return owner;
            }
        }
        return null;
    }
}
