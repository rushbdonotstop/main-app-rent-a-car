package com.example.user.dto;

public class TokenDTO {
    private String jwt;

    public  TokenDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
