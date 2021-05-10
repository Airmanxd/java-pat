package com.example.Practice20;

import lombok.Getter;
import lombok.NonNull;
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
    @NonNull
    private String name;
    @Column
    @NonNull
    private String cityName;
    @OneToMany(mappedBy = "postOffice")
    public List<Departure> departures;

}
