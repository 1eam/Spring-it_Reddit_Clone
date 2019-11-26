package com.vega.springit.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

//    @RequestMapping("/")
//    public String home(){
//        return "Hello, Spring Boot 2";
//    }
//

    @GetMapping("/home")
    public String hello(Model model) {
        model.addAttribute("title", "Hello Tymeleaf! - heres where i can pass my dynamic content");
        return "home";
    }

}