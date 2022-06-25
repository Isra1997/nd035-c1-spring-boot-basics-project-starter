package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {

    CredentialMapper credentialMapper;
    UserMapper userMapper;
    EncryptionService encryptionService;


    public CredentialService(CredentialMapper credentialMapper,UserMapper userMapper,EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.userMapper = userMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getAllCredentials(){
        return credentialMapper.getAllCredentials();
    }

    public boolean isCredentialAvailable(int credentialId){
        return credentialMapper.getCredentialById(credentialId) == null;
    }

    public int createCredential(Credential credential){
        if(credentialMapper.getCredentialWithUsername(credential.getUsername())!=null){
            return -1;
        }else{
            //get the current user
            User user = userMapper.getUser(getUsername());
            //encrypt the entered password
            SecureRandom secureRandom = new SecureRandom();
            byte [] salt = new byte[16];
            secureRandom.nextBytes(salt);
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedSalt);
            //create the credential
            Credential newCredential = new Credential(null,credential.getUrl(), credential.getUsername(),encodedSalt ,encryptedPassword,user.getUserId());
            return credentialMapper.createCredential(newCredential);
        }
    }

    public void deleteCredential(Integer credentialId){
        credentialMapper.deleteCredential(credentialId);
    }


    public void updateCredential(Credential credential){
        //get the current user
        User user = userMapper.getUser(getUsername());
        //encrypt the entered password
        SecureRandom secureRandom = new SecureRandom();
        byte [] salt = new byte[16];
        secureRandom.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedSalt);
        //create the credential
        Credential newCredential = new Credential(credential.getCredentialId(),credential.getUrl(), credential.getUsername(),encodedSalt ,encryptedPassword,user.getUserId());
        credentialMapper.updateCredential(newCredential);
    }

    //get the current user's username
    public String getUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }


}
