package com.alex.vis.internetshop.controller;

import com.alex.vis.internetshop.service.SessionObjectHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final SessionObjectHolder sessionObjectHolder;

    @RequestMapping({"", "/"})
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("myID") == null) {
            String uuid = UUID.randomUUID().toString();
            session.setAttribute("myID", uuid);
            System.out.println("Generated UUID -> " + uuid);
        }
        model.addAttribute("uuid", session.getAttribute("myID"));

        model.addAttribute("amountClicks", sessionObjectHolder.getAmountOfClicks());
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
