package com.vega.springit.controller;

import com.vega.springit.domain.User;
import com.vega.springit.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/profile")
    public String profile() {
        return "auth/profile";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("success", false);
        return "auth/register";
    }
    @PostMapping("/register")
    public String registerNewUser(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors()){
            // show validation errors
            logger.info("Validation errors were found while registering a new user");
            model.addAttribute("user", user);
            model.addAttribute("validationErrors", bindingResult.getAllErrors());
            return "auth/register";

        } else {
            // Register new user
        }
        return null;
    }

}
