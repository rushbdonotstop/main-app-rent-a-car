package com.example.request.dto.user;

public class Penalty {

    private Long id;

    private Long total;

    private PenaltyStatus penaltyStatus;

    public Penalty() {
    }

    public PenaltyStatus getPenaltyStatus() {
        return penaltyStatus;
    }

    public void setPenaltyStatus(PenaltyStatus penaltyStatus) {
        this.penaltyStatus = penaltyStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
