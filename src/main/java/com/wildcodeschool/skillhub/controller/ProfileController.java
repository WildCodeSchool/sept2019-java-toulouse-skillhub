package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.repository.ProfileRepository;
import com.wildcodeschool.skillhub.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProfileController {

    ProfileRepository profileRepository = new ProfileRepository();

    @GetMapping("/profile")
    public String profile(Model out, HttpSession session) {

        out.addAttribute("avatars", profileRepository.findAllAvatars());
        out.addAttribute("skills", profileRepository.findAllSkills());
        out.addAttribute("user", session.getAttribute("user"));
        return "profile";
    }

    @PostMapping("/profile")
    public String updateUser(Model out, @RequestParam Long userId, @RequestParam String nickname, @RequestParam String password, @RequestParam String avatarUrl, @RequestParam List<Long> skillsId) {

       out.addAttribute("user", UserRepository.updateUser(userId, nickname, password, avatarUrl, skillsId));

        return "redirect:/profile";
    }
}