package org.AirTickets.contollers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.AirTickets.DTO.JwtRequest;
import org.AirTickets.DTO.JwtResponse;
import org.AirTickets.DTO.UserDTO;
import org.AirTickets.Entity.User;
import org.AirTickets.exceptions.AppError;
import org.AirTickets.services.RegistrationService;
import org.AirTickets.services.UsersService;
import org.AirTickets.util.JWTutil;
import org.AirTickets.util.UserValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthContoller {

    private final UserValidator userValidator;
    private final RegistrationService registrationService;
    private final UsersService usersService;
    private final ModelMapper modelMapper;
    private final JWTutil jwTutil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest){
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Неправильный логин или парот"),HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = usersService.loadUserByUsername(authRequest.getLogin());
        String token = jwTutil.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }

/*
    @ResponseBody
    @PostMapping("/registration")
    public Map<String,String> addUserInDb(@RequestBody @Valid UserDTO userDTO,
                              BindingResult bindingResult){
        User user = convertToUser(userDTO);

        user = usersService.splittingSNP(user);
        System.out.println(user.toString());

        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            bindingResult.getModel().keySet().forEach(s -> System.out.println(bindingResult.getModel().get(s)));
            return Map.of("message","Ошибка!");
        }

        registrationService.register(user);

        String token = jwTutil.generateToken(user.getLogin());
        return Map.of("jwt-token", token);
    }

 */
/*
    @ResponseBody
    @PostMapping(value = "/login")
    public Map<String,String> performLogin(@RequestParam String login,@RequestParam String password, HttpServletResponse response) throws IOException {

        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(login,
                        password);

        try {
            authenticationManager.authenticate(authInputToken);
            System.out.println(password);
            System.out.println(login);
        }catch (BadCredentialsException exp ){
            System.out.println(password);
            System.out.println(login);
            return Map.of("message","Incorrect credentials!");
        }

        String token = jwTutil.generateToken();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("src/main/resources/static/json/token.json"),Map.of("token",token));

        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true); // Запрет доступа из JavaScript
        cookie.setSecure(true); // Требуется HTTPS
        cookie.setMaxAge(3600 * 12); // Установка срока действия
        response.addCookie(cookie);


        return Map.of("jwt-token", token);
    }

 */

    public User convertToUser(UserDTO userDTO){
        return this.modelMapper.map(userDTO,User.class);
    }
}