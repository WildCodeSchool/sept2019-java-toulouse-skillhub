package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.repository.AnswerRepository;
import com.wildcodeschool.skillhub.repository.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {

    private QuestionRepository questionRepository = new QuestionRepository();
    private AnswerRepository answerRepository = new AnswerRepository();

    @GetMapping("/question")
    public String getQuestion(Model model, @RequestParam Long id) {

        model.addAttribute("question", questionRepository.findQuestion(id));
        model.addAttribute("answer", answerRepository.findAnswers(id));

        return "answer";
    }
}
