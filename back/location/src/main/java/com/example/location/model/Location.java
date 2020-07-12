package com.example.location.model;

import javax.persistence.*;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="state", referencedColumnName = "id")
    private State state;

    @ManyToOne
    @JoinColumn(name="city", referencedColumnName = "id")
    private City city;

    @ManyToOne
    @JoinColumn(name="street", referencedColumnName = "id")
    private Street street;

    public Location() {
    }

    public Location(State state, City city, Street street) {
        this.state = state;
        this.city = city;
        this.street = street;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", state=" + state +
                ", city=" + city +
                ", street=" + street +
                '}';
    }

}
