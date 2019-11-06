package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.repository.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FeedController {

    private QuestionRepository repository = new QuestionRepository();

    @GetMapping("/feed")
    public String getOwn(Model model, @RequestParam Long userId) {

        List<Long> skillsId = new ArrayList<>();
        skillsId.add(1l);
        skillsId.add(3l);
        skillsId.add(5l);

        model.addAttribute("own", repository.findAllOwn(userId));
        model.addAttribute("other", repository.findAllOther(skillsId, userId));
        return "feed";
    }
}
