package org.AirTickets.contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.AirTickets.models.Tickets;
import org.AirTickets.models.User;
import org.AirTickets.services.CitiesService;
import org.AirTickets.services.TicketsService;
import org.AirTickets.services.UsersService;
import org.AirTickets.util.TicketsValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("/mainpage")
@SessionAttributes("ticket")
public class TicketsContollers {

    private final CitiesService citiesService;
    private final TicketsService ticketsService;
    private final UsersService usersService;
    private final TicketsValidator ticketsValidator;

    @Autowired
    public TicketsContollers(CitiesService citiesService, TicketsService ticketsService, UsersService usersService, TicketsValidator ticketsValidator) {
        this.citiesService = citiesService;
        this.ticketsService = ticketsService;
        this.usersService = usersService;
        this.ticketsValidator = ticketsValidator;
    }


    @ModelAttribute("ticket")
    public Tickets getTicket() {
        User user = usersService.getAuthUser();
        Tickets ticket = new Tickets();
        ticket.setOwner(user);
        return ticket;
    }


    @GetMapping()
    public String index(Model model){

        //Создание нового билета
        Tickets tickets = new Tickets();
        User user = usersService.getAuthUser();
        tickets.setOwner(user);

        model.addAttribute("ticket",tickets);
        model.addAttribute("cities",citiesService.findAll());

        return "index";
    }
    @GetMapping("/buy_ticket")
    private String buyTicket(@ModelAttribute("ticket") @Valid Tickets tickets,
                             BindingResult bindingResult,Model model){

        ticketsValidator.validate(tickets,bindingResult);

        if(bindingResult.hasErrors()){
            model.addAttribute("cities",citiesService.findAll());
            return "index";
        }

        User user = usersService.getAuthUser();
        tickets.setOwner(user);
        tickets.setPrice(ticketsService.getPrice());

        return "mainpages/buy";
    }

    @PostMapping("/buy_ticket")
    public String showInfoTicket(@ModelAttribute("ticket") Tickets ticket){


        User user = usersService.getAuthUser();
        ticket.setOwner(user);

        System.out.println("работает");
        System.out.println(ticket.toString());

        ticketsService.save(ticket);

        return "index";
    }

    @ResponseBody
    @GetMapping("/test/{id}")
    private void test(@PathVariable("id") int id) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("src/main/resources/static/json/infoTicket.json"), ticketsService.findById(id));
    }

}
