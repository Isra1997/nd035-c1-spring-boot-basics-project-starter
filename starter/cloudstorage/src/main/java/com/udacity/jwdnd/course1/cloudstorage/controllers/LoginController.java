package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginView(@ModelAttribute("Success") Object success, Model model){
        model.addAttribute("Success", success);
        return "login";
    }


    @GetMapping("/login-error")
    public String login(Model model){
        System.out.println("kolo el deh");
        model.addAttribute("isError",true);
        return  "login";
    }



    @GetMapping("/logout")
    public String logout(Model model){
        model.addAttribute("isLoggedOut",true);
        return  "login";
    }


}
