package com.example.Practice24.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="PostOffices")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
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
