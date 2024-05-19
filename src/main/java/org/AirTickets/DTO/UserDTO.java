package org.AirTickets.DTO;


import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String name;

    private String surname;

    private String patronymic;

    @NotNull
    @Size(min = 8, max = 8, message = "Пароль должен быть восьмизначным")
    private String password;

    private String email;

    @NotNull
    private String login;
}
