package com.example.Practice24.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Departures")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
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
