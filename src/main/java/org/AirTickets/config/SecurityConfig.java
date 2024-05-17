package org.AirTickets.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {
    //Настраивает аунтификацию
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(); //TODO
    }
}
