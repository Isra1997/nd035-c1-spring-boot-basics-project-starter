package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignUpPage(){
        return "signup";
    }

    @PostMapping
    public String signUp(@ModelAttribute("UserFormData") User user,Model model){
        String errorMessage = "";
        if(!userService.isUserAvailable(user.getUsername())){
            errorMessage = " This username is already taken please try another username";
        }else{
            int affectRows = userService.createUser(user);
            if(affectRows<0){
                errorMessage = "Opps..Something went wrong please try again!";
            }
        }

        if(!errorMessage.isEmpty()){
            model.addAttribute("errorMessage",errorMessage);
        }else{
            model.addAttribute("Success", true);
        }
        return "signup";
    }
}
