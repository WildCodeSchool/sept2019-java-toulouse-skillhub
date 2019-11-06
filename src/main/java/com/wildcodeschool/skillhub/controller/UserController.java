package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.repository.QuestionRepository;
import com.wildcodeschool.skillhub.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private UserRepository repository = new UserRepository();

    @GetMapping("/connect")
    public String connect(Model model, @RequestParam(name="username") String username, @RequestParam(name="password") String password ) {

        Long userId = repository.checkUser(username, password);
        if (userId != 0) {
            List<Long> skillsId = new ArrayList<>();
            skillsId.add(1l);
            skillsId.add(3l);
            skillsId.add(5l);

            model.addAttribute("userId", 1l);
            return "redirect:/feed";
        }
        else {
            return "index";
        }
    };

}
