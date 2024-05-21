package org.AirTickets.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.AirTickets.security.UsersDetails;
import org.AirTickets.services.UsersService;
import org.AirTickets.util.JWTutil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTutil jwTutil;
    private final UsersService usersService;

    public JWTFilter(JWTutil jwTutil,UsersService usersService) {
        this.jwTutil = jwTutil;
        this.usersService = usersService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if(authHeader !=null && !authHeader.isBlank() && authHeader.startsWith("Bearer ")) {
                String jwt = authHeader.substring(7);

                if(jwt.isBlank()){
                    response.sendError(response.SC_BAD_REQUEST,"Недопустимый jwt токен");
                }else {
                    try {
                    String login = jwTutil.validateTokenAndRetrieveClaim(jwt);
                    UserDetails userDetails = usersService.loadUserByUsername(login);

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    userDetails.getPassword(),
                                    userDetails.getAuthorities());

                    if (SecurityContextHolder.getContext().getAuthentication() == null){
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }catch (JWTVerificationException exc){
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST,
                                "Недопустимый JWT токен");
                    }
           }
        }
        filterChain.doFilter(request,response);
    }
}
