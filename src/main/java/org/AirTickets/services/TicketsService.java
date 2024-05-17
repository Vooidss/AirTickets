package org.AirTickets.services;

import jakarta.transaction.Transactional;
import org.AirTickets.Repositories.TicketsRepository;
import org.AirTickets.models.Tickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TicketsService{

    private final TicketsRepository ticketsRepository;

    @Autowired
    public TicketsService(TicketsRepository ticketsRepository) {
        this.ticketsRepository = ticketsRepository;
    }

    public List<Tickets> findall(){
        return ticketsRepository.findAll();
    }

    public void save(Tickets tickets){
        ticketsRepository.save(tickets);
    }
}
