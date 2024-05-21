package org.AirTickets.services;

import jakarta.transaction.Transactional;
import org.AirTickets.Repositories.CountriesRepository;
import org.AirTickets.Entity.Countries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CountriesService {

    private final CountriesRepository countriesRepository;

    @Autowired
    public CountriesService(CountriesRepository countriesRepository) {
        this.countriesRepository = countriesRepository;
    }

    public void save(Countries countries){
        countriesRepository.save(countries);
    }
}
