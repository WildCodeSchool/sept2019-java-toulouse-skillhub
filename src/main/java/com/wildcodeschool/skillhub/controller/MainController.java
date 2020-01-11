package com.wildcodeschool.skillhub.controller;

import com.google.common.hash.Hashing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        String pass = "soninlaw27";
        pass = Hashing.sha256()
                .hashString(pass, StandardCharsets.UTF_8)
                .toString();
        System.out.println(pass);

        return "index";
    }


    @GetMapping("/about")
    public String about(Model model, HttpSession session) {
        if (session.getAttribute("user") == null) {
            model.addAttribute("connected", false);
        } else {
            model.addAttribute("connected", true);
        }
        return "about";
    }
}
