package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.entity.User;
import com.wildcodeschool.skillhub.repository.QuestionRepository;
import com.wildcodeschool.skillhub.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class FeedController {

    private QuestionRepository qRepository = new QuestionRepository();
    private UserRepository userRepository = new UserRepository();

    @GetMapping("/feed")
    public String getFeed(Model model, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "index";
        }
        User user = (User)session.getAttribute("user");
        model.addAttribute("own", qRepository.findAllOwn(user.getUserId()));
        model.addAttribute("other", qRepository.findAllOther(user.getSkillsId(), user.getUserId()));
        return "feed";
    }
}
