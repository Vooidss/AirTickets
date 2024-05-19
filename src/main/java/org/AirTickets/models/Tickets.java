package org.AirTickets.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tickets")
@Getter
@Setter
public class Tickets {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "owner_id",referencedColumnName = "id")
    private User owner;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "sendingcity_id",referencedColumnName = "id")
    private Cities sendingCity;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "arrivalcity_id",referencedColumnName = "id")
    private Cities arrivalCity;

    @NotNull
    @Column(name = "sendingdate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String sendingDate;

    @Column(name = "arrivaldate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String arrivalDate;

}
