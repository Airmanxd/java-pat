package com.example.Practice17;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="PostOffices")
@Getter
@Setter
@RequiredArgsConstructor
public class PostOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String name;
    @Column
    private String cityName;
    @OneToMany(mappedBy = "postOffice")
    public List<Departure> departures;
}