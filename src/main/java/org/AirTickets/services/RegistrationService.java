package org.AirTickets.services;

import jakarta.transaction.Transactional;
import org.AirTickets.Repositories.UsersRepository;
import org.AirTickets.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService
{
    private final UsersRepository usersRepository;

    @Autowired
    public RegistrationService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public void register(User user){
        usersRepository.save(user);
    }
}
