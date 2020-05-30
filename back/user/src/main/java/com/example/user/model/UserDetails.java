package com.example.user.model;


import com.example.user.model.enums.Privilege;
import com.example.user.model.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "business_num")
    private String businessNum;

    @Column(name = "vehicle_num", nullable = false)
    private String vehicleNum;

    @Column(name = "user_type", nullable = false)
    private UserType userType;

    private transient List<Privilege> privilegeList;

    public UserDetails() {
    }

    public UserDetails(String fullName, String address, String businessNum, String vehicleNum, UserType userType, List<Privilege> privilegeList) {
        this.fullName = fullName;
        this.address = address;
        this.businessNum = businessNum;
        this.vehicleNum = vehicleNum;
        this.userType = userType;
        this.privilegeList = privilegeList;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBusinessNum() {
        return businessNum;
    }

    public void setBusinessNum(String businessNum) {
        this.businessNum = businessNum;
    }

    public String getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(String vehicleNum) {
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

    @Override
    public String toString() {
        return "UserDetails{" +
                "fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", businessNum='" + businessNum + '\'' +
                ", vehicleNum='" + vehicleNum + '\'' +
                ", userType=" + userType +
                ", privilegeList=" + privilegeList +
                '}';
    }
}
