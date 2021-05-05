package com.example.Practice15;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DepartureController {
    @Autowired
    private DepartureRepository departureRepository;

    public void create_departure(String type, String date){
        Departure temp =  new Departure(type, date);
        departureRepository.save(temp);
    }

    public List<Departure> getDepartures() {
        return departureRepository.findAll();
    }

    public void deleteDepartureByName(Integer id){
        departureRepository.deleteById(id);
    }
}
