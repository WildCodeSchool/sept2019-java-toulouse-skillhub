package com.wildcodeschool.skillhub.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public String register(Model model) {

        return "inscription";
    }
}
