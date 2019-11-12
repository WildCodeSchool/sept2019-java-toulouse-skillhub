package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.entity.User;
import com.wildcodeschool.skillhub.repository.AnswerRepository;
import com.wildcodeschool.skillhub.repository.QuestionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;


@Controller
public class QuestionController {

    private QuestionRepository questionRepository = new QuestionRepository();
    private AnswerRepository answerRepository = new AnswerRepository();

    @GetMapping("/question")
    public String getQuestion(Model model, @RequestParam Long id, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "index";
        }
        int answerNumber = answerRepository.findAnswers(id).size();
        model.addAttribute("question", questionRepository.findQuestion(id));
        model.addAttribute("answer", answerRepository.findAnswers(id));
        model.addAttribute("answerNumber", answerNumber);

        return "question";
    }

    @PostMapping("/answer")
    public String postAnswer(Model model, @RequestParam Long id,
                             @RequestParam String body, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "index";
        }
        User user = (User)session.getAttribute("user");
        model.addAttribute("answer", answerRepository.saveAnswer(id, body, new Date(System.currentTimeMillis()),
                user.getUserId()));
        model.addAttribute("question", questionRepository.findQuestion(id));

        return "redirect:question?id=" + id;
    }

    @GetMapping("/resolved")
    public String resolvedAnswer(Model model, @RequestParam Long questionId, HttpSession session) {
        // true or false, recupérer dans model attribut
        if (session.getAttribute("user") == null) {
            return "index";
        }
        answerRepository.setResolved(questionId);

        return "redirect:/feed";
    }
}
