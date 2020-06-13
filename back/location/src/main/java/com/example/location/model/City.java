package com.example.location.model;

import javax.persistence.*;

@Entity
public class City implements Comparable<City> {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(name="city", nullable = false, unique = true)
    private String value;

    public City() {
    }

    public City(String value) {
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
        return "City{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }

    @Override
    public int compareTo(City o) {
        if (this.getId() == o.getId())
            return 0;
        if (this.getId() > o.getId()) {
            return 1;
        }
        if (this.getId() < o.getId()) {
            return -1;
        }
        return 0;
    }
}
