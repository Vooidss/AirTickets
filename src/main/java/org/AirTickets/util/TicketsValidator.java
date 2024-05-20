package org.AirTickets.util;

import jakarta.persistence.Column;
import org.AirTickets.models.Tickets;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TicketsValidator  implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Tickets tickets = (Tickets) target;

        if(!(tickets.getCountpeople() > 0)){
            errors.rejectValue("countpeople","","Количество пассажиров должно быть больше 0");
        }

    }
}
