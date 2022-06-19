package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("Select * from USERS where username=#{username}")
    User getUser(String  username);

    @Insert("Insert into USERS(username,password,salt, firstname,lastname) " +
            "Values(#{username},#{password},#{salt},#{firstname},#{lastname})")
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    int createUser(User user);

}
