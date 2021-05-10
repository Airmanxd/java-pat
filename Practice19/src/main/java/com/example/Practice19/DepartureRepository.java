package com.example.Practice19;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartureRepository extends JpaRepository<Departure, Integer> {
    List<Departure> findAllByPostOfficeId(int postOfficeId);
}
