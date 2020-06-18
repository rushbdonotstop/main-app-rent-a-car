package com.example.request.DTO;

import java.util.ArrayList;
import java.util.List;

public class BundleDTO {

    private Long id;
    private List<RequestForFrontDTO> requestsList = new ArrayList<>();;
    private float totalCost;
    private String username;

    public BundleDTO() {
    }

    public BundleDTO(Long id, List<RequestForFrontDTO> requestsList, float totalCost, String username) {
        this.id = id;
        this.requestsList = requestsList;
        this.totalCost = totalCost;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RequestForFrontDTO> getRequestsList() {
        return requestsList;
    }

    public void setRequestsList(List<RequestForFrontDTO> requestsList) {
        this.requestsList = requestsList;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "BundleDTO{" +
                "id=" + id +
                ", requestsList=" + requestsList +
                ", totalCost=" + totalCost +
                ", username='" + username + '\'' +
                '}';
    }
}
