package com.example.user.dto;

public class UserDetailsDTO {
    private Long id;
    private String fullName;
    private String address;
    private String businessNum;
    private String vehicleNum;
    private String userType;

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(Long id, String fullName, String address, String businessNum, String vehicleNum, String userType) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.businessNum = businessNum;
        this.vehicleNum = vehicleNum;
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
