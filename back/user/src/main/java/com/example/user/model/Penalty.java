package com.example.user.model;

import javax.persistence.*;

@Entity
public class Penalty {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long total;

    public Penalty() {
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

    @Override
    public String toString() {
        return "Penalty{" +
                "id=" + id +
                ", total=" + total +
                '}';
    }
}
