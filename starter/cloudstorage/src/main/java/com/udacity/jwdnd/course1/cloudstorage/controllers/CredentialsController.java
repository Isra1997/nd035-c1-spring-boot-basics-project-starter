package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/credentials")
public class CredentialsController {
    CredentialService credentialService;
    EncryptionService encryptionService;

    public CredentialsController(CredentialService credentialService,EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.encryptionService = encryptionService;
    }


    @PostMapping
    String createOrUpdateCredential( @ModelAttribute("credentialData") Credential credentialData, Model model){
        int created = 0;
        //check id update or create
        if(credentialData.getCredentialId()== null){
            created = credentialService.createCredential(credentialData);

        }else{
            created = 1;
            credentialService.updateCredential(credentialData);
        }
        //check if the update and create operations are successful
        if(created == 0){
            model.addAttribute("message","Opss.. Something went wrong please try again later!");
            model.addAttribute("display", false);
        }else if(created == -1){
            model.addAttribute("message","Cannot add a credential with the same url and username.");
            model.addAttribute("display", false);
        }else{
            model.addAttribute("display", true);
        }
        model.addAttribute("credentials",credentialService.getAllCredentials());
        model.addAttribute("encryptionService",encryptionService);
        return "result";
    }



    @GetMapping(value = "/{credentialId}")
    String deleteNote(@PathVariable Integer credentialId, @ModelAttribute("credentialData") Credential credentialData, Model model){
        //delete credential
        credentialService.deleteCredential(credentialId);
        //check if the delete operation is successful
        if(credentialService.isCredentialAvailable(credentialData.getCredentialId())){
            model.addAttribute("display", true);
        }else{
            model.addAttribute("message","Opss.. Something went wrong please try again later!");
            model.addAttribute("display", false);
        }
        //set the new credentials list
        model.addAttribute("credentials", credentialService.getAllCredentials());
        return "result";
    }



}
