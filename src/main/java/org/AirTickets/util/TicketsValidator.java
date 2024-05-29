package org.AirTickets.util;

import jakarta.persistence.Column;
import org.AirTickets.models.Tickets;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Date;

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

        if(tickets.getSendingDate().before(new Date())){
            errors.rejectValue("sendingDate","","Дата должна не раньше ненешней");
        }

        if( tickets.getArrivalDate() != null && tickets.getArrivalDate().before(tickets.getSendingDate())){
            errors.rejectValue("arrivalDate","","Дата прибытия должна быть после даты вылета");
        }

    }
}
