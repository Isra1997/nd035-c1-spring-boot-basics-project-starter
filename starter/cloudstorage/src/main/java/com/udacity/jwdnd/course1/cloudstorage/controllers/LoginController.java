package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class LoginController {

    @GetMapping(value = {"/","/login"})
    public String getLoginView(@ModelAttribute("Success") Object success, Model model){
        model.addAttribute("Success", success);
        return "login";
    }


    @GetMapping("/login-error")
    public String login(Model model){
        model.addAttribute("isError",true);
        return  "login";
    }

    @GetMapping("/logout")
    public String logout(Model model){
        model.addAttribute("isLoggedOut",true);
        return  "login";
    }


}
