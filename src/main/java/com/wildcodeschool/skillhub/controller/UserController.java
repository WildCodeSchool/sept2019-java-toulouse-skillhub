package com.wildcodeschool.skillhub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @GetMapping("/connect")
    public String connect(Model model, @RequestParam(name="username") String userName, @RequestParam(name="password") String password ) {

    };

}
