package com.example.location.repository;

import com.example.location.model.City;
import com.example.location.model.Location;
import com.example.location.model.State;
import com.example.location.model.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByStateAndCityAndStreet(State state, City city, Street street);
}
