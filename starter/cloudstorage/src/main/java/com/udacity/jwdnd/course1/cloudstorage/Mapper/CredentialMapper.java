package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {
    @Select("Select * from CREDENTIALS")
    List<Credential> getAllCredentials();

    @Select("Select * From CREDENTIALS where username=#{username}")
    Credential getCredentialWithUsername(String  username);

    @Update("Update CREDENTIALS set  url=#{url} ,username=#{username}, password=#{password}  where credentialId=#{credentialId}")
    void updateNote(Credential credential);

    @Insert("Insert into CREDENTIALS(url,username,key,password,userId) Values (#{url},#{username},#{key},#{password},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "credentialId")
    int createCredential(Credential credential);

    @Delete("Delete from CREDENTIALS where credentialId=#{credentialId}")
    void deleteCredential(Integer credentialId);
}
