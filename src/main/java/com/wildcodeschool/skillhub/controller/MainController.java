package com.wildcodeschool.skillhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @PostMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/feed")
    public String feed() {
        return "feed";
    }

    @PostMapping("/subscribe")
    public String subscribe() {
        return "subscribe";
    }

    @PostMapping("/question")
    public String question() {
        return "question";
    }

    @PostMapping("/about")
    public String about() {
        return "about";
    }

    @PostMapping("/ask")
    public String ask() {
        return "ask";
    }

    @PostMapping("/profile")
    public String profile() {
        return "profile";
    }
}
