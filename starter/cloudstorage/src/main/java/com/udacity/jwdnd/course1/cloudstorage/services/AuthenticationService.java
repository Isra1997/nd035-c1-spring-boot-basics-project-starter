package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService implements AuthenticationProvider {

    UserMapper userMapper;
    HashService hashService;

    public AuthenticationService(UserMapper userMapper,HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userMapper.getUser(username);
        if(user !=null){
            if(hashService.getHashedValue(password,user.getSalt()).equals(user.getPassword())){
                return new UsernamePasswordAuthenticationToken(username,password, new ArrayList<>());
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
