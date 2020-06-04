package com.example.location.repository;

import com.example.location.model.City;
import com.example.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l FROM Location l WHERE l.state.value = ?1 AND l.city.value = ?2 AND l.street.value = ?3")
    Location findByStateAndCityAndStreet(String state, String city, String street);

    @Query("SELECT l.city FROM Location l WHERE l.state.id = ?1")
    SortedSet<City> getCitiesByState(Long stateId);
}
