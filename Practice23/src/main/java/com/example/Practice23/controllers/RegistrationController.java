package com.example.Practice23.controllers;

import com.example.Practice23.models.UserDTO;
import com.example.Practice23.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String registration(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        return "registration";
    }

    @PostMapping
    public String addUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO, Model model, @CookieValue("JSESSIONID") String cookie) {
        if (!userDTO.getPassword().equals(userDTO.getMatchingPassword())){
            model.addAttribute("passwordError", "Passwords don't match");
            return "registration";
        }
        if (!userService.register(userDTO, cookie)){
            model.addAttribute("usernameError", "User with this name alreasy esists!");
            return "registration";
        }
        return "redirect:/welcome";
    }
}

