package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

@Service
public class FileService {
    FileMapper fileMapper;
    UserMapper userMapper;

    public FileService(FileMapper fileMapper,UserMapper userMapper) {
        this.fileMapper = fileMapper;
        this.userMapper = userMapper;
    }

    public boolean isFileNameAvailable(String filename){
        Integer userId = userMapper.getUser(getUsername()).getUserId();
        return fileMapper.getFileByFilename(filename,userId) == null;
    }

    public File getFileById(Integer fileId){
        return  fileMapper.getFileById(fileId);
    }

    public int uploadedFile(FileForm fileForm) throws IOException {
        //get user
        User user = userMapper.getUser(getUsername());
        MultipartFile multipartFile = fileForm.getFile();

        //check if a file with the same name exists
        if(fileMapper.getFileByFilename(multipartFile.getOriginalFilename(),user.getUserId())!=null){
            return -1;
        }else{
            //create the file
            Integer userId = user.getUserId();
            String contentType = multipartFile.getContentType();
            String filename = multipartFile.getOriginalFilename();
            String fileSize = String.valueOf(multipartFile.getSize());
            byte[] fileData= multipartFile.getBytes();
            File file = new File(null,filename,contentType,fileSize,userId,fileData);
            return fileMapper.createFile(file);
        }
    }

    public File downloadFile(String filename){
       return fileMapper.getFileByFilename(filename,userMapper.getUser(getUsername()).getUserId());
    }

    public List<File> getAllFiles(){
        return fileMapper.getAllFiles();
    }

    public void deleteFile(Integer fileId){
        fileMapper.deleteFile(fileId);
    }


    public String getUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
