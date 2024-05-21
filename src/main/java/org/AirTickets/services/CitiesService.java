package org.AirTickets.services;

import jakarta.transaction.Transactional;
import org.AirTickets.Repositories.CitiesRepository;
import org.AirTickets.Entity.Cities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CitiesService {

    private final CitiesRepository citiesRepository;

    @Autowired
    public CitiesService(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    public List<Cities> findAll(){
        return citiesRepository.findAll();
    }

    public List<Cities> findByNameStringWith(String input){
        return citiesRepository.findByNameStartingWith(input);
    }

}
