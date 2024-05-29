package org.AirTickets.contollers;

import jakarta.validation.Valid;
import org.AirTickets.models.User;
import org.AirTickets.services.RegistrationService;
import org.AirTickets.services.UsersService;
import org.AirTickets.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthContoller {

    private final UserValidator userValidator;
    private final RegistrationService registrationService;
    private final UsersService usersService;

    @Autowired
    public AuthContoller(UserValidator userValidator, RegistrationService registrationService, UsersService usersService) {
        this.userValidator = userValidator;
        this.registrationService = registrationService;
        this.usersService = usersService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user){
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String addUserInDb(@ModelAttribute("user") @Valid User user,
                              BindingResult bindingResult){


        if(!(user.getLogin().isEmpty())) {

            user = usersService.splittingSNP(user);

            userValidator.validate(user, bindingResult);

            if (bindingResult.hasErrors()) {
                return "auth/registration";
            }

            registrationService.register(user);

        }
            return "auth/login";
    }
}