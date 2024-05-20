package org.AirTickets.contollers;

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


@Controller
@RequestMapping("/mainpage")
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

    @GetMapping()
    public String index(Model model){

        //Создание нового билета
        Tickets tickets = new Tickets();

        model.addAttribute("ticket",tickets);
        model.addAttribute("cities",citiesService.findAll());

        return "index";
    }


    @PostMapping()
    public String save(@ModelAttribute("ticket") @Valid Tickets tickets,
                        BindingResult bindingResult){

        ticketsValidator.validate(tickets,bindingResult);

        if(bindingResult.hasErrors()){
            return "index";
        }

        //Берем авторизованного пользователя
        User user = usersService.getAuthUser();

        tickets.setOwner(user);

        return "mainpages/buy";
    }

    @GetMapping("/buy_ticket")
    private String buyTicket(@ModelAttribute("ticket") @Valid Tickets tickets){

        System.out.println(tickets.toString());

        return "mainpages/buy";
    }

    @PostMapping("/buy_ticket")
    public String showInfoTicket(@ModelAttribute("ticket") @Valid Tickets tickets,
                       BindingResult bindingResult){

        System.out.println("работает");

        return "mainpages/buy";
    }
}
