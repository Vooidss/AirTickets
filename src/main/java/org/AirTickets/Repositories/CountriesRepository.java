package org.AirTickets.Repositories;

import org.AirTickets.models.Countries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountriesRepository extends JpaRepository<Countries,Integer>{
}
