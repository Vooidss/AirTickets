package org.AirTickets.services;

import jakarta.transaction.Transactional;
import org.AirTickets.Repositories.UsersRepository;
import org.AirTickets.Entity.User;
import org.AirTickets.security.UsersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    public void update(User user){
        usersRepository.save(user);
    }

    public User getAuthUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UsersDetails usersDetails = (UsersDetails) authentication.getPrincipal();

        return usersDetails.getUser();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<User> users = usersRepository.findByLogin(login);

        if(users.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }

        return new UsersDetails(users.get());
    }

    public User splittingSNP(User user){
        List<String> snp = Arrays.stream(user.getNSP().split(" ")).toList();

        user.setSurname(snp.get(0));
        user.setName(snp.get(1));

        if(snp.size() == 3) {
            user.setPatronymic(snp.get(2));
        }

        return user;
    }
}
