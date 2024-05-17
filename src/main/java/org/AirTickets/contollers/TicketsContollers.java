package org.AirTickets.contollers;

import jakarta.validation.Valid;
import org.AirTickets.Repositories.CitiesRepository;
import org.AirTickets.models.Cities;
import org.AirTickets.models.Tickets;
import org.AirTickets.models.User;
import org.AirTickets.services.CitiesService;
import org.AirTickets.services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/buy_ticket")
public class TicketsContollers {

    private final TicketsService ticketsService;
    private final CitiesService citiesService;

    @Autowired
    public TicketsContollers(TicketsService ticketsService, CitiesService citiesService) {
        this.ticketsService = ticketsService;
        this.citiesService = citiesService;
    }

    @GetMapping()
    private String index(Model model){

        model.addAttribute("cities",citiesService.findAll());
        model.addAttribute("tickets",new Tickets());

        return "index";
    }

    @PostMapping()
    private String save(Tickets tickets){

        ticketsService.save(tickets);

        return "redirect:/buy_ticket";
    }
}
