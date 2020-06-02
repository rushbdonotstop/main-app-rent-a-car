package com.example.user.dto;

import com.example.user.model.enums.Privilege;
import com.example.user.model.enums.UserType;

import java.util.List;

public class UserCreateVehicleDTO {

    private UserType userType;
    private List<Privilege> privilegeList;
    private int vehicleNum;

    public UserCreateVehicleDTO() {
    }

    public UserCreateVehicleDTO(UserType userType, List<Privilege> privilegeList, int vehicleNum) {
        this.userType = userType;
        this.privilegeList = privilegeList;
        this.vehicleNum = vehicleNum;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public List<Privilege> getPrivilegeList() {
        return privilegeList;
    }

    public void setPrivilegeList(List<Privilege> privilegeList) {
        this.privilegeList = privilegeList;
    }

    public int getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(int vehicleNum) {
        this.vehicleNum = vehicleNum;
    }

    @Override
    public String toString() {
        return "UserCreateVehicleDTO{" +
                "userType=" + userType +
                ", privilegeList=" + privilegeList +
                ", vehicleNum=" + vehicleNum +
                '}';
    }
}
