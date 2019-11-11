package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.repository.RegisterRepository;
import com.wildcodeschool.skillhub.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class ProfileController {

    @GetMapping("/profile")
    public String register(Model model) {

        return "profile";
    }

    @PostMapping("/profile")
    public String registerUser(Model out, @RequestParam Long userId, @RequestParam String nickname, @RequestParam String password, @RequestParam String avatarUrl, @RequestParam List<Long> skillsId) {

       out.addAttribute("user", UserRepository.updateUserById(userId, nickname, password, avatarUrl, skillsId));

        return "redirect:/profile";
    }
}