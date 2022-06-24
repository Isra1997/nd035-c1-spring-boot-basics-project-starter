package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public boolean isFileNameAvaliable(String filename){
        return fileMapper.getFileByFilename(filename) == null;
    }

    public void uploadedFile(File file){
//        fileMapper.createFile(file);
    }

    public File downloadFile(String filename){
       return fileMapper.getFileByFilename(filename);
    }

    public List<File> getAllFiles(){
        return fileMapper.getAllFiles();
    }

    public void deleteFile(Integer fileId){
        fileMapper.deleteFile(fileId);
    }
}
