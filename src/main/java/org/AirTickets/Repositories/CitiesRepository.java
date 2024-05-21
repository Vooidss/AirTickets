package org.AirTickets.Repositories;

import org.AirTickets.Entity.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitiesRepository extends JpaRepository<Cities,Integer> {
    List<Cities> findByNameStartingWith(String input);
}
