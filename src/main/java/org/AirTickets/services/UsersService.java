package org.AirTickets.services;

import jakarta.transaction.Transactional;
import org.AirTickets.Repositories.UsersRepository;
import org.AirTickets.models.User;
import org.AirTickets.security.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void save(User user){
        usersRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = usersRepository.findByName(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }

        return new UsersDetails(user.get());
    }
}
