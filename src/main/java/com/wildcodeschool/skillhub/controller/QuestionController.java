package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.repository.AnswerRepository;
import com.wildcodeschool.skillhub.repository.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class QuestionController {

    private QuestionRepository questionRepository = new QuestionRepository();
    private AnswerRepository answerRepository = new AnswerRepository();

    @GetMapping("/question")
    public String getQuestion(Model model, @RequestParam Long id) {

        model.addAttribute("question", questionRepository.findQuestion(id));
        model.addAttribute("answer", answerRepository.findAnswers(id));

        int answerNumber = answerRepository.findAnswers(id).size();

        model.addAttribute("answerNumber", answerNumber);

        return "answer";
    }

    @PostMapping("/question")
    public String postQuestion(Model model, @RequestParam Long id,
                               @RequestParam String body,
                               @RequestParam String getDate) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date date = new java.util.Date();
        String currentDate = dateFormat.format(date);
        Date thisDate = new SimpleDateFormat("dd/MM/yyyy").parse(currentDate);

        model.addAttribute("questionById", answerRepository.saveAnswer(id, body, (java.sql.Date) thisDate));

        return "question";
    }
}
