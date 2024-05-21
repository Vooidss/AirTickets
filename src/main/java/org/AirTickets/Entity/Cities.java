package org.AirTickets.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "cities")
@Getter
@Setter
public class Cities {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_id",referencedColumnName = "id")
    private Countries country;

    @JsonIgnore
    @OneToMany(mappedBy = "sendingCity")
    private List<Tickets> sendingCity;

    @JsonIgnore
    @OneToMany(mappedBy = "arrivalCity")
    private List<Tickets> arrivalCity;

    @Override
    public String toString() {
        return  name  + "," + country.getNameCountry();
    }
}
