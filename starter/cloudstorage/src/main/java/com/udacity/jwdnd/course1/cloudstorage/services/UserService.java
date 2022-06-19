package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
@Service
public class UserService {

    UserMapper userMapper;
    HashService hashService;


    public UserService(UserMapper userMapper,HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public boolean isUserAvailable(String username){
        return userMapper.getUser(username) == null;
    }

    public int createUser(User user){
        SecureRandom secureRandom = new SecureRandom();
        byte [] salt = new byte[16];
        secureRandom.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
         return userMapper.createUser(new User(null, user.getUsername(),encodedSalt,hashedPassword, user.getFirstname(),user.getLastname()));
    }
}
