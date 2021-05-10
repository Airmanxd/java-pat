package com.example.Practice18;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@RestController
public class PostOfficeController {
    PostOfficeService postOfficeService;

    @PostMapping("/addPO")
    public PostOffice add(@RequestParam("name") String name, @RequestParam("cityName") String cityName) {
        PostOffice temp = new PostOffice(name, cityName);
        postOfficeService.save(temp);
        return temp;
    }

    @GetMapping("/getPOs")
    public List<PostOffice> getPostOffices() {
        return postOfficeService.getAll();
    }

    @GetMapping(value="/departure/{departureid}/PO")
    public @ResponseBody PostOffice getPostOfficeByDeparture(@PathVariable("departureid") int departureid){
        return this.getPostOfficeByDeparture(departureid);
    }

    @GetMapping(value = "/POsSortedBy/{fieldName}")
    public @ResponseBody List<PostOffice> getSortedPostOfficesByField(@PathVariable("fieldName") String fieldName) {
        return postOfficeService.getSortedPostOfficesByField(fieldName);
    }

    @DeleteMapping("/deletePO/{id}")
    public void remove(@PathVariable("id") int id){
        postOfficeService.remove(id);
    }

    @Autowired
    public void setPostOfficeService(PostOfficeService postOfficeService) {
        this.postOfficeService = postOfficeService;
    }
}
