package org.AirTickets.contollers;

import org.AirTickets.models.Tickets;
import org.AirTickets.models.User;
import org.AirTickets.security.UsersDetails;
import org.AirTickets.services.CitiesService;
import org.AirTickets.services.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/buy_ticket")
public class TicketsContollers {

    private final CitiesService citiesService;
    private final TicketsService ticketsService;

    @Autowired
    public TicketsContollers(CitiesService citiesService, TicketsService ticketsService) {
        this.citiesService = citiesService;
        this.ticketsService = ticketsService;
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
    public  String save(Tickets tickets){
        /*
        //Получение пользователя, который авторизовался
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails usersDetails = (UsersDetails) authentication.getPrincipal();
        User user = usersDetails.getUser();

        tickets.setOwner(user);
        System.out.println(tickets.getOwner().getName() + tickets.getOwner().getLogin());

    */
        return "redirect:/buy_ticket";
    }


}
