package org.AirTickets.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "tickets")
@Getter
@Setter
public class Tickets {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date sendingDate;

    @Column(name = "arrivaldate")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arrivalDate;

    @Column(name = "countpeople")
    @NotNull()
    private int countpeople;

    @Column(name = "price")
    private String price;


    @Override
    public String toString() {
        return "Tickets{" +
                "id=" + id +
                ", owner=" + owner.getLogin() +
                ", sendingCity=" + sendingCity.getName() +
                ", arrivalCity=" + arrivalCity.getName() +
                ", sendingDate=" + sendingDate +
                ", arrivalDate=" + arrivalDate +
                ", countpeople=" + countpeople +
                ", price='" + price + '\'' +
                '}';
    }
}
