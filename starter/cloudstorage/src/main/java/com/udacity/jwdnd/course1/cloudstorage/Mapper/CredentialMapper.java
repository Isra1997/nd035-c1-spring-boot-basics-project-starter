package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("Select * from CREDENTIALS")
    List<Credential> getAllCredentials();

    @Select("Select * from CREDENTIALS where credentialId=#{credentialId}")
    Credential getCredentialById(int credentialId);

    @Select("Select * From CREDENTIALS where username=#{username}")
    Credential getCredentialWithUsername(String  username);

    @Update("Update CREDENTIALS set  url=#{url} ,username=#{username}, password=#{password}, key=#{key}  where credentialId=#{credentialId}")
    void updateCredential(Credential credential);

    @Insert("Insert into CREDENTIALS(url,username,key,password,userId) Values (#{url},#{username},#{key},#{password},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int createCredential(Credential credential);

    @Delete("Delete from CREDENTIALS where credentialId=#{credentialId}")
    void deleteCredential(Integer credentialId);
}
