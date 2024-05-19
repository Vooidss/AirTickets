package org.AirTickets.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.util.Date;
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

    @OneToMany(mappedBy = "owner")
    private List<Tickets> tickets;

    @Transient
    private String NSP;

    public String getNSP(){
        return NSP;
    }
    public String getNsp(){
        return surname + " " + name + " " + patronymic;
    }

}
