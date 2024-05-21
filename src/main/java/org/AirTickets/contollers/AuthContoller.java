package org.AirTickets.contollers;

import jakarta.validation.Valid;
import org.AirTickets.DTO.UserDTO;
import org.AirTickets.models.User;
import org.AirTickets.services.RegistrationService;
import org.AirTickets.services.UsersService;
import org.AirTickets.util.JWTutil;
import org.AirTickets.util.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthContoller {

    private final UserValidator userValidator;
    private final RegistrationService registrationService;
    private final UsersService usersService;
    private final ModelMapper modelMapper;
    private final JWTutil jwTutil;

    public AuthContoller(UserValidator userValidator, RegistrationService registrationService, UsersService usersService,
                         ModelMapper modelMapper, JWTutil jwTutil) {
        this.userValidator = userValidator;
        this.registrationService = registrationService;
        this.usersService = usersService;
        this.modelMapper = modelMapper;
        this.jwTutil = jwTutil;
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
    public Map<String,String> addUserInDb(@RequestBody @Valid UserDTO userDTO,
                              BindingResult bindingResult){
        User user = convertToUser(userDTO);

        user = usersService.splittingSNP(user);
        System.out.println(user.toString());

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            bindingResult.getModel().keySet().forEach(s -> System.out.println(bindingResult.getModel().get(s)));
            return Map.of("message","Ошибка!");
        }

        registrationService.register(user);

        String token = jwTutil.generateToken(user.getLogin());
        return Map.of("jwt-token", token);
    }

    public User convertToUser(UserDTO userDTO){
        return this.modelMapper.map(userDTO,User.class);
    }
}