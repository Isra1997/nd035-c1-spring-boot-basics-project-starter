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
    HashService hashService;


    public CredentialService(CredentialMapper credentialMapper,UserMapper userMapper,HashService hashService) {
        this.credentialMapper = credentialMapper;
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public List<Credential> getAllCredentials(){
        return credentialMapper.getAllCredentials();
    }

    public int createCredential(Credential credential){
        if(credentialMapper.getCredentialWithUsername(credential.getUsername())!=null){
            return 0;
        }else{
            //get the current user
            User user = userMapper.getUser(getUsername());
            //hash the entered password
            SecureRandom secureRandom = new SecureRandom();
            byte [] salt = new byte[16];
            secureRandom.nextBytes(salt);
            String encodedSalt = Base64.getEncoder().encodeToString(salt);
            String hashedPassword = hashService.getHashedValue(credential.getPassword(), encodedSalt);
            //create the credential
            Credential newCredential = new Credential(null,credential.getUrl(), credential.getUsername(),encodedSalt ,hashedPassword,user.getUserId());
            return credentialMapper.createCredential(newCredential);
        }
    }

    public void deleteCredential(Integer credentialId){
        credentialMapper.deleteCredential(credentialId);
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
