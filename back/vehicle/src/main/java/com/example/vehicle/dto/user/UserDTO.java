package com.example.vehicle.dto.user;

import javax.persistence.*;


public class UserDTO {


    private Long id;
    private String username;

    public UserDTO() {
    }

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "OwnerDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
