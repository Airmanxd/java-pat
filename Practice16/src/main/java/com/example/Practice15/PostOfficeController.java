package com.example.Practice15;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostOfficeController {
    @Autowired
    private PostOfficeRepository postOfficeRepository;

    public void createPostOffice(String name, String cityName){
        PostOffice temp = new PostOffice(name, cityName);
        postOfficeRepository.save(temp);
    }

    public List<PostOffice> getPostOffices() {
        return postOfficeRepository.findAll();
    }

    public void deletePostOfficeByName(Integer id){
        postOfficeRepository.deleteById(id);
    }
}
