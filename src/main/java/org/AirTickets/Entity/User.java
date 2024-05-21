package org.AirTickets.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @NotNull
    @Column(name = "password")
    private String password;


    @Column(name = "email")
    private String email;

    @Column(name = "login")
    @NotNull
    private String login;

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Tickets> tickets;

    @Column(name = "NSP")
    private String NSP;

    @Column(name = "phone")
    private String phone;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
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