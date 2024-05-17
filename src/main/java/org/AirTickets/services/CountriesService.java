package org.AirTickets.services;

import jakarta.transaction.Transactional;
import org.AirTickets.Repositories.CountriesRepository;
import org.AirTickets.models.Countries;
import org.AirTickets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
