package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.entity.Question;
import com.wildcodeschool.skillhub.entity.User;
import com.wildcodeschool.skillhub.repository.AnswerRepository;
import com.wildcodeschool.skillhub.repository.ProfileRepository;
import com.wildcodeschool.skillhub.repository.QuestionRepository;
import com.wildcodeschool.skillhub.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class QuestionController {

    private QuestionRepository questionRepository = new QuestionRepository();
    private AnswerRepository answerRepository = new AnswerRepository();
    private UserRepository userRepository = new UserRepository();
    private ProfileRepository profileRepository = new ProfileRepository();

    @GetMapping("/question")
    public String getQuestion(Model model, @RequestParam Long id, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "index";
        }
        int answerNumber = answerRepository.findAnswers(id).size();
        model.addAttribute("question", questionRepository.findQuestion(id));
        model.addAttribute("answer", answerRepository.findAnswers(id));
        model.addAttribute("answerNumber", answerNumber);

        User user = (User) session.getAttribute("user");
        model.addAttribute("userIdConnect", user.getUserId());

        return "question";
    }

    @PostMapping("/askQuestion")
    public String postQuestion(Model model, HttpSession session, @RequestParam String title,
                               @RequestParam String body,
                               @RequestParam Long skill) {

        if (session.getAttribute("user") == null) {
            return "index";
        }

        User user = (User) session.getAttribute("user");
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());

        Question question = questionRepository.askQuestion(title, body, sqlDate, false, user.getUserId());
        questionRepository.addSkillToQuestion(question.getQuestionId(), skill);

        return "redirect:/feed";
    }

    @GetMapping("/askQuestion")
    public String getQuestion(Model model, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "index";
        }

        Map<Long, String> skills = profileRepository.findAllSkills();
        System.out.println("skillsize" + skills.size());

        model.addAttribute("skills", skills);

        User user = (User) session.getAttribute("user");
        model.addAttribute("ask", userRepository.getUserById(user.getUserId()));

        return "ask";
    }

    @PostMapping("/answer")
    public String postAnswer(Model model, @RequestParam Long id,
                             @RequestParam String body, HttpSession session) {
        if (session.getAttribute("user") == null) {
            return "index";
        }
        User user = (User) session.getAttribute("user");
        java.sql.Date sqlDate = new java.sql.Date(System.currentTimeMillis());
        model.addAttribute("answer", answerRepository.saveAnswer(id, body, sqlDate,
                user.getUserId()));
        model.addAttribute("question", questionRepository.findQuestion(id));

        return "redirect:question?id=" + id;
    }

    @GetMapping("/resolved")
    public String resolvedAnswer(Model model, @RequestParam Long questionId, HttpSession session) {

        if (session.getAttribute("user") == null) {
            return "index";
        }
        answerRepository.setResolved(questionId);

        return "redirect:/feed";
    }
}