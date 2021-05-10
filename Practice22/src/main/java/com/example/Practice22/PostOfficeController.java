package com.example.Practice22;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/PostOffice")
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
