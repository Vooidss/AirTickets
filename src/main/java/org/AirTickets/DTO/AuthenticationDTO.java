package org.AirTickets.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationDTO {
    @NotNull
    private String login;

    @NotNull
    private String password;
}
