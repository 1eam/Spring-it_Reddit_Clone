package com.vega.springit.controller;



import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HomeController {

//    @RequestMapping("/")
//    public String home(){
//        return "Hello, Spring Boot 2";
//    }
//

    @GetMapping("/")
    public String hello(Model model, HttpServletRequest request) {
        model.addAttribute("message", "Hello World!");
        return "index, but not html please";
    }

}