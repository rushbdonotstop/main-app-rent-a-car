package com.example.location.repository;

import com.example.location.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l FROM Location l WHERE l.state.value = ?1 AND l.city.value = ?2 AND l.street.value = ?3")
    Location findByStateAndCityAndStreet(String state, String city, String street);
}
