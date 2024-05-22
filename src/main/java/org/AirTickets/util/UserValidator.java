package org.AirTickets.util;

import org.AirTickets.Repositories.UsersRepository;
import org.AirTickets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final UsersRepository usersRepository;

    @Autowired
    public UserValidator(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        Optional<User> users = usersRepository.findByLogin(user.getLogin());

        if(users.isPresent()){
            errors.rejectValue("login","","Пользователь с таким логином уже существует");
        }
    }
}
