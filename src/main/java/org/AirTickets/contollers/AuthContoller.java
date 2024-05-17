package org.AirTickets.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthContoller {

    @GetMapping("/login")
    public String logiPage(){
        return "auth/login";
    }
}
