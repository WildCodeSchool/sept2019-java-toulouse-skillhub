package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.entity.User;
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
    UserRepository userRepository = new UserRepository();

    @GetMapping("/profile")
    public String profile(Model out, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "index";
        }
        out.addAttribute("avatars", profileRepository.findAllAvatars());
        out.addAttribute("skills", profileRepository.findAllSkills());
        out.addAttribute("user", session.getAttribute("user"));
        return "profile";
    }

    @PostMapping("/update-profile")
    public String updateUser(Model out, HttpSession session, @RequestParam String nickname, @RequestParam String password, @RequestParam Long avatar, @RequestParam(name="skill", defaultValue = "-1") List<Integer> skillsId) {

        if (session.getAttribute("user") == null) {
            return "index";
        }
        User user = (User)session.getAttribute("user");
        userRepository.updateUser(user.getUserId(), nickname, password, avatar, skillsId, user.getSkillsId());
        session.setAttribute("user", userRepository.getUserById(user.getUserId()));
        return "redirect:/profile";
    }
}