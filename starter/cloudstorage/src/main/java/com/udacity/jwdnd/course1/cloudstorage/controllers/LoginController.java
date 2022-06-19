package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginView(){
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
