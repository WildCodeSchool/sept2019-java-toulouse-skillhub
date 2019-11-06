package com.wildcodeschool.skillhub.controller;

import com.wildcodeschool.skillhub.repository.QuestionRepository;
import com.wildcodeschool.skillhub.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private UserRepository repository = new UserRepository();

    @GetMapping("/connect")
    public String connect(Model model, @RequestParam(name="username") String username, @RequestParam(name="password") String password ) {

        Long userId = repository.checkUser(username, password);
        if (userId != 0) {
            model.addAttribute("own", repository.);
            return "feed";
        }
        else {
            return "index";
        }
    };

}
