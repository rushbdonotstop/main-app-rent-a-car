package com.example.user.dto;

public class UserPrivilegeRequestDTO {
    private String userPrivilege;

    public UserPrivilegeRequestDTO(String userPrivilege) {
        this.userPrivilege = userPrivilege;
    }

    public UserPrivilegeRequestDTO() {
    }

    public String getUserPrivilege() {
        return userPrivilege;
    }

    public void setUserPrivilege(String userPrivilege) {
        this.userPrivilege = userPrivilege;
    }
}
