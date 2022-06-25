package com.udacity.jwdnd.course1.cloudstorage.models;

import org.springframework.web.multipart.MultipartFile;

public class FileForm {

    MultipartFile file;

    public FileForm(MultipartFile multipartFile) {
        this.file = multipartFile;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
