package ru.arbiter2008.librarymanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/")
//    public String home() {
//        return "index"; // TODO
//    }
}
