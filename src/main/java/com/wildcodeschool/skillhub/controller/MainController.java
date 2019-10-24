package com.wildcodeschool.skillhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/feed")
    public String feed() {
        return "feed";
    }

    @GetMapping("/subscribe")
    public String subscribe() {
        return "subscribe";
    }

    @GetMapping("/answer")
    public String answer() {
        return "answer";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/question")
    public String question() {
        return "question";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
}
