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
    private String password;

    private String email;

    @NotNull
    private String login;

    private String NSP;

    private String phone;


    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", NSP='" + NSP + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
