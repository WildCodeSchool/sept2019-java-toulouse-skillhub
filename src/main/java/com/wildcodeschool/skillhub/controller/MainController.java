package com.wildcodeschool.skillhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/about")
    public String about(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("connected", false);
        } else {
            model.addAttribute("connected", true);
        }
        return "about";
    }
}
