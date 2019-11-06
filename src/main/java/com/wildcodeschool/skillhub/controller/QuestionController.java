package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.repository.AnswerRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

public class QuestionController {

    private AnswerRepository answerRepository = new AnswerRepository();

    @PostMapping("/question")
    public String postWizard(Model model,
                             @RequestParam String answerArea) {

        /*model.addAttribute("answer", AnswerRepository.save(body));*/

        return "answer";
    }

}
