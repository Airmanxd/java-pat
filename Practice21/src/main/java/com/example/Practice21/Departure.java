package com.example.Practice21;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Departures")
@Getter
@Setter
@RequiredArgsConstructor
public class Departure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    @NonNull
    private String type;
    @Column
    @NonNull
    private String departureDate;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="postOffice_id")
    public PostOffice postOffice;

}
