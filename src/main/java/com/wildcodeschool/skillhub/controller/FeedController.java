package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.entity.User;
import com.wildcodeschool.skillhub.repository.QuestionRepository;
import com.wildcodeschool.skillhub.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FeedController {

    private QuestionRepository qRepository = new QuestionRepository();
    private UserRepository uRepository = new UserRepository();

    @GetMapping("/feed")
    public String getFeed(Model model, @RequestParam Long userId) {

        User user = uRepository.getUser(userId);
        model.addAttribute("own", qRepository.findAllOwn(userId));
        model.addAttribute("other", qRepository.findAllOther(user.getSkillsId(), userId));
        return "feed";
    }
}
