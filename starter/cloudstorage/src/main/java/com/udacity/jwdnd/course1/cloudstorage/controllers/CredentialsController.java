package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/credentials")
public class CredentialsController {
    CredentialService credentialService;

    public CredentialsController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @GetMapping
   void getAllCredentials(@ModelAttribute("CredentialData") Credential credentialData, Model model){
        model.addAttribute("results", credentialService.getAllCredentials());
    }

    @PostMapping
    void addCredential(@ModelAttribute("CredentialData") Credential credentialData, Model model){

    }
}
