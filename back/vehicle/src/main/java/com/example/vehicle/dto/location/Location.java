package com.example.vehicle.dto.location;


public class Location {

    private Long id;

    private State state;

    private City city;

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

    public com.example.vehicle.xmlmodel.location.Location toXML(Location location){

        com.example.vehicle.xmlmodel.location.Location locationXML = new com.example.vehicle.xmlmodel.location.Location();
        com.example.vehicle.xmlmodel.location.state.State state = new com.example.vehicle.xmlmodel.location.state.State();
        com.example.vehicle.xmlmodel.location.city.City city = new com.example.vehicle.xmlmodel.location.city.City();
        com.example.vehicle.xmlmodel.location.street.Street street = new com.example.vehicle.xmlmodel.location.street.Street();

        state.setId(location.getState().getId());
        state.setValue(location.getState().getValue());

        city.setId(location.getCity().getId());
        city.setValue(location.getCity().getValue());

        street.setId(location.getStreet().getId());
        street.setValue(location.getStreet().getValue());

        locationXML.setState(state);
        locationXML.setCity(city);
        locationXML.setStreet(street);

        locationXML.setId(id);

        return locationXML;
    }
}
