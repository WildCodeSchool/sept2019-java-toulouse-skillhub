package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.entity.User;
import com.wildcodeschool.skillhub.repository.ProfileRepository;
import com.wildcodeschool.skillhub.repository.UserRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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
    public String registerUser(Model out, HttpSession session, @RequestParam String nickname, @RequestParam String password, @RequestParam String passwordConfirmation, @RequestParam String avatar, @RequestParam(name="skill", defaultValue = "-1") List<Long> skillsId) {

        if (!(userRepository.checkPasswordFormat(password))) {
            out.addAttribute("pwdFormat", true);
            System.out.println("salut !");
            out.addAttribute("avatars", profileRepository.findAllAvatars());
            out.addAttribute("skills", profileRepository.findAllSkills());
            return "register";
        }

        if (!(userRepository.passwordCheck(password, passwordConfirmation))) {
            out.addAttribute("passwordCheck", true);
            out.addAttribute("avatars", profileRepository.findAllAvatars());
            out.addAttribute("skills", profileRepository.findAllSkills());
            return "register";
        }

        User user = profileRepository.saveUser(nickname, password, avatar, skillsId);
        session.setAttribute("user", user);
        return "redirect:/feed";
    }
}
