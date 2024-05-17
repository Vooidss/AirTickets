package org.AirTickets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    //Настраивает аунтификацию

     private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //конфигрурируется Spring Security
        //конфигурируется авторизацию
        return http.csrf().disable()
                .authorizeRequests() // Настраивается авторизация ( до and )
                .requestMatchers("/auth/login","/error","/css/login.css","/css/index.css").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/auth/login") // Настраивается страница логина
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/buy_ticket",true)
                .failureUrl("/auth/login?error")
                .and()
                .build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        return provider;
    }


    @Bean
    public PasswordEncoder getPasswordEncoder(){
         return NoOpPasswordEncoder.getInstance();
    }
}
