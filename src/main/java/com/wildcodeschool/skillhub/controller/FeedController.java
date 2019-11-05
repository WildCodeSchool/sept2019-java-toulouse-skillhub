package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.entity.Question;
import com.wildcodeschool.skillhub.repository.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FeedController {

    private QuestionRepository repository = new QuestionRepository();

    @GetMapping("/feed")
    public String getOwn(Model model) {

        model.addAttribute("own", repository.findOwn(3l));

        return "feed";
    }
}
