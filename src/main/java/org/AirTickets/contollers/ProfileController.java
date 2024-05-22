package org.AirTickets.contollers;

import jakarta.validation.Valid;
import org.AirTickets.models.User;
import org.AirTickets.security.UsersDetails;
import org.AirTickets.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UsersService usersService;

    @Autowired
    public ProfileController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public String showInfo(Model model){

        User user = usersService.getAuthUser();

        model.addAttribute("user",user);

        return "/mainpages/profile";
    }
    @PatchMapping("/edit")
    public String edit(@ModelAttribute("user") @Valid User user,
                       BindingResult bindingResult){

        user = usersService.splittingSNP(user);
        usersService.update(user);

        return "/mainpages/profile";
    }
}
