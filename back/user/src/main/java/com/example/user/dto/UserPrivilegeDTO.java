package com.example.user.dto;


import java.util.ArrayList;
import java.util.List;

public class UserPrivilegeDTO {
    private Long userId;
    private List<String> userPrivileges = new ArrayList<>();

    public UserPrivilegeDTO() {
    }

    public UserPrivilegeDTO(Long userId, List<String> userPrivileges) {
        this.userId = userId;
        this.userPrivileges = userPrivileges;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<String> getUserPrivileges() {
        return userPrivileges;
    }

    public void setUserPrivileges(List<String> userPrivileges) {
        this.userPrivileges = userPrivileges;
    }

    public void addPrivilege(String privilege) {
        userPrivileges.add(privilege);
    }
}
