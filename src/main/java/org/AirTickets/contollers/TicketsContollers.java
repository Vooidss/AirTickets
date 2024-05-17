package org.AirTickets.contollers;

import org.AirTickets.models.Tickets;
import org.AirTickets.services.CitiesService;
import org.AirTickets.services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
    public String index(Model model){

        Tickets tickets = new Tickets();

        model.addAttribute("ticket",tickets);
        model.addAttribute("cities",citiesService.findAll());

        return "index";
    }


    @PostMapping()
    public  String save(Tickets tickets){
        return "redirect:/buy_ticket";
    }


}
