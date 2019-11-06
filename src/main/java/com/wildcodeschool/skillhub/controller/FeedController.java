package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.repository.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedController {

    private QuestionRepository repository = new QuestionRepository();

    @GetMapping("/feed")
    public String getOwn(Model model, @RequestParam Long userId) {

        model.addAttribute("own", repository.findAllOwn(userId));
        return "feed";
    }
}
