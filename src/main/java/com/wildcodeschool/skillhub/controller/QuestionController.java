package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.entity.User;
import com.wildcodeschool.skillhub.repository.AnswerRepository;
import com.wildcodeschool.skillhub.repository.QuestionRepository;
import com.wildcodeschool.skillhub.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class QuestionController {

    private QuestionRepository questionRepository = new QuestionRepository();
    private AnswerRepository answerRepository = new AnswerRepository();
    private UserRepository userRepository = new UserRepository();

    @GetMapping("/question")
    public String getQuestion(Model model, @RequestParam Long id, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "index";
        }
        int answerNumber = answerRepository.findAnswers(id).size();
        model.addAttribute("question", questionRepository.findQuestion(id));
        model.addAttribute("answer", answerRepository.findAnswers(id));
        model.addAttribute("answerNumber", answerNumber);

        User user = (User)session.getAttribute("user");
        model.addAttribute("userIdConnect", user.getUserId());

        return "question";
    }

    @PostMapping("/askQuestion")
    public String postQuestion(Model model, HttpSession session, @RequestParam String title,
                               @RequestParam String body) {

        if (session.getAttribute("user") == null) {
            return "index";
        }


        //TODO: recupérer list skill, 

        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

        User user = (User)session.getAttribute("user");
        model.addAttribute("askQuestion", questionRepository.askQuestion(title, body, sqlDate, false, user.getUserId()));

        return "redirect:feed";
    }

/*    @GetMapping("/askQuestion")
    public String postQuestion(Model model, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "index";
        }

        User user = (User)session.getAttribute("user");
        model.addAttribute("ask", userRepository.getUserById(user.getUserId()));

        return "ask";
    }*/



    @GetMapping("/askQuestion")
    public String postQuestion(HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "index";
        }

        return "testForm";
    }




    @PostMapping("/answer")
    public String postAnswer(Model model, @RequestParam Long id,
                             @RequestParam String body, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "index";
        }
        User user = (User)session.getAttribute("user");
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        model.addAttribute("answer", answerRepository.saveAnswer(id, body, sqlDate,
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
