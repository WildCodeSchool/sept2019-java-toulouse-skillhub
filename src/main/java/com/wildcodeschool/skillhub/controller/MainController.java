package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
