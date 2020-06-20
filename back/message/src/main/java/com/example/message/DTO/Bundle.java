package com.example.message.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.List;


public class Bundle {

    private Long id;
    List<RequestDTO> requests;

    public Bundle(List<RequestDTO> requests) {
        this.requests = requests;
    }

    public Bundle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RequestDTO> getRequests() {
        return requests;
    }

    public void setRequests(List<RequestDTO> requests) {
        this.requests = requests;
    }

    @Override
    public String toString() {
        return "Bundle{" +
                "id=" + id +
                ", requests=" + requests +
                '}';
    }
}
