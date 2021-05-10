package com.example.Practice16;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Prac15 {
    @GetMapping("/home")
    public @ResponseBody String home_page(){
        return "home";
    }
}
