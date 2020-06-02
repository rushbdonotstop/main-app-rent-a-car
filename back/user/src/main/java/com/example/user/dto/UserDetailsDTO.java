package com.example.user.dto;

public class UserDetailsDTO {
    private Long id;
    private String fullName;
    private String adress;
    private String businessNum;
    private String vehicleNum;
    private String userType;

    public UserDetailsDTO() {
    }

    public UserDetailsDTO(Long id, String fullName, String adress, String businessNum, String vehicleNum, String userType) {
        this.id = id;
        this.fullName = fullName;
        this.adress = adress;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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
