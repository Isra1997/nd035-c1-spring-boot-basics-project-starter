package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    NoteService noteService;
    CredentialService credentialService;
    EncryptionService encryptionService;

    public HomeController(NoteService noteService, CredentialService credentialService,EncryptionService encryptionService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }

    @GetMapping
    public String getHome(@ModelAttribute("noteData")Note noteData,@ModelAttribute("credentialData")Credential credentialData, Model model){
        model.addAttribute("notes", noteService.getAllNotes());
        model.addAttribute("credentials", credentialService.getAllCredentials());
        model.addAttribute("display",false);
        model.addAttribute("encryptionService",encryptionService);
        return "home";
    }



}
