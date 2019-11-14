package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.repository.ProfileRepository;
import com.wildcodeschool.skillhub.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RegisterController {

    ProfileRepository profileRepository = new ProfileRepository();
    UserRepository userRepository = new UserRepository();

    @GetMapping("/register")
    public String register(Model out) {

        out.addAttribute("avatars", profileRepository.findAllAvatars());
        out.addAttribute("skills", profileRepository.findAllSkills());
        return "register";
    }

    @PostMapping("/submit")
    public String registerUser(Model out, @RequestParam String nickname, @RequestParam String password, @RequestParam String passwordConfirmation, @RequestParam String avatar, @RequestParam(name="skill", defaultValue = "-1") List<Long> skillsId) {

        if (!(userRepository.passwordCheck(password, passwordConfirmation))) {
            out.addAttribute("passwordCheck", true);
            out.addAttribute("avatars", profileRepository.findAllAvatars());
            out.addAttribute("skills", profileRepository.findAllSkills());
            return "register";
        }

        if (userRepository.checkExistingUsername(nickname)) {
            out.addAttribute("checkUsername", true);
            out.addAttribute("avatars", profileRepository.findAllAvatars());
            out.addAttribute("skills", profileRepository.findAllSkills());
            return "register";
        }

        profileRepository.saveUser(nickname, password, avatar, skillsId);
        return "redirect:/";
    }
}
