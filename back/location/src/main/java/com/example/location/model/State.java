package com.example.location.model;

import javax.persistence.*;

@Entity
public class State {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name="value", nullable = false, unique = true)
    private String value;

    public State() {
    }

    public State(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "State{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
