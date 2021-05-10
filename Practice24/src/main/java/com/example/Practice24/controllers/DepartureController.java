package com.example.Practice24.controllers;

import com.example.Practice24.models.Departure;
import com.example.Practice24.services.DepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartureController {
    DepartureService departureService;
    @PostMapping("PostOffice/{PO}/addDeparture")
    public Departure add(@PathVariable("PO") int id, @RequestParam("type") String type, @RequestParam("departureDate") String departureDate) {
        Departure temp = new Departure(type, departureDate);
        departureService.save(temp, id);
        return temp;
    }

    @GetMapping("/getDepartures")
    public List<Departure> getPostOffices() {
        return departureService.getAll();
    }

    @GetMapping(value = "/DeparturesSortedBy/{fieldName}")
    public @ResponseBody List<Departure> getSortedDeparturesByField(@PathVariable("fieldName") String fieldName) {
        return departureService.getSortedDeparturesByField(fieldName);
    }

    @DeleteMapping("/deleteDeparture/{id}")
    public void remove(@PathVariable("id") int id){
        departureService.delete(id);
    }

    @Autowired
    public void setDepartureService(DepartureService departureService) {
        this.departureService = departureService;
    }
}
