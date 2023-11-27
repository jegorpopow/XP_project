package com.example.aboba.controllers;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MainController {
    @GetMapping("/game")
    public String game(Model model) {
        return "main";
    }

    @PostMapping("/make_bet")
    public String makeBet(Model model, HttpServletRequest  request) {
        String user  =request.getParameter("user");
        String type  =request.getParameter("type");
        //int start = Integer.parseInt(request.getParameter("start"));
        System.out.println(user + type);
        return "main";
    }

    @GetMapping("/")
    public String homePage(Model model) {
        return "home";
    }
}
