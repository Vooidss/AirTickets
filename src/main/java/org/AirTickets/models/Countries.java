package org.AirTickets.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class Countries {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotNull
    private String nameCountry;

    @JsonIgnore
    @OneToMany(mappedBy = "country")
    private List<Cities> cities;

    public Countries(){}

    public Countries(String nameCountry,List<Cities> cities) {
        this.nameCountry = nameCountry;
        this.cities = cities;
    }
}
