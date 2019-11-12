package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.repository.RegisterRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RegisterController {

    RegisterRepository registerRepository = new RegisterRepository();

    @GetMapping("/register")
    public String register(Model out) {

        out.addAttribute("avatars", registerRepository.findAllAvatars());
        out.addAttribute("skills", registerRepository.findAllSkills());
        return "register";
    }

    @PostMapping("/submit")
    public String registerUser(Model out, @RequestParam String nickname, @RequestParam String password, @RequestParam String avatar, @RequestParam(name="skill", defaultValue = "-1") List<Long> skillsId) {

        registerRepository.saveUser(nickname, password, avatar, skillsId);

        return "index";
    }
}
