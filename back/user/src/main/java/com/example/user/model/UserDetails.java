package com.example.user.model;


import com.example.user.model.enums.Privilege;
import com.example.user.model.enums.UserType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = true)
    private String fullName;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @Column(name = "business_num")
    private String businessNum;

    @Column(name = "vehicle_num", nullable = true)
    private int vehicleNum;

    @Column(name = "user_type", nullable = true)
    private UserType userType;

    @OneToMany()
    @JoinColumn(name = "userdetail_id") //so the new table doesnt get created
    private Set<Penalty> penalties;

    private transient List<Privilege> privilegeList;

    public UserDetails() {
    }


    public UserDetails(String fullName, String address, String email, String businessNum, int vehicleNum, UserType userType, Set<Penalty> penalties, List<Privilege> privilegeList) {
        this.fullName = fullName;
        this.address = address;
        this.email = email;
        this.businessNum = businessNum;
        this.vehicleNum = vehicleNum;
        this.userType = userType;
        this.penalties = penalties;
        this.privilegeList = privilegeList;
    }

    public void setPenalties(Set<Penalty> penalties) {
        this.penalties = penalties;
    }

    public Set<Penalty> getPenalties() {
        return penalties;
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

    public int getVehicleNum() {
        return vehicleNum;
    }

    public void setVehicleNum(int vehicleNum) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
