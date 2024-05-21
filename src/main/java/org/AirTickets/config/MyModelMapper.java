package org.AirTickets.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;
@Configuration
public class MyModelMapper {

    @Bean
    public ModelMapper modelMapper(){
        return new org.modelmapper.ModelMapper();
    }
}
