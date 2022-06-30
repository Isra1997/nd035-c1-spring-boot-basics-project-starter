package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.FileForm;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FileController {
    FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public String createOrUpdateFile(@ModelAttribute("file") FileForm fileForm, Model model) throws IOException {
        System.out.println(fileForm.getFile().getSize());
        int created = fileService.uploadedFile(fileForm);
        if(created > 0){
            System.out.println("File create");
            model.addAttribute("display", true);
        }else if(created == -1){
            model.addAttribute("message", "Please upload a file with a different name, as the uploaded file already exists");
            model.addAttribute("display", false);
        }else if(created == -2) {
            model.addAttribute("message", "The file size exceeds the size of 1048576 bytes.");
            model.addAttribute("display", false);

        }
        else{
            model.addAttribute("message", "Opps...something went wrong please try again later!");
            model.addAttribute("display", false);
        }
        model.addAttribute("files", fileService.getAllFiles());
        return "result";
    }

    @GetMapping(value = "/file/{fileId}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public byte[] getFile(@PathVariable Integer fileId, @ModelAttribute("fileData") File fileData, Model model){
        File file = fileService.getFileById(fileId);
        if(file!=null){
            model.addAttribute("display",true);

        }else{
            model.addAttribute("display", false);
            model.addAttribute("message","Ops... Something went wrong please try again later!");
        }
        return file.getFileData();
    }


    @GetMapping(value = "/{fileId}")
    public String deleteFile(@PathVariable Integer fileId,@ModelAttribute("fileData") File fileData,Model model){
        //delete the given's file Id
        fileService.deleteFile(fileId);
        if(fileService.isFileNameAvailable(fileData.getFilename())){
            model.addAttribute("display",true);
        }else{
            model.addAttribute("message","Opps..Something went wrong please try again later!");
            model.addAttribute("display",false);
        }

        model.addAttribute("files",fileService.getAllFiles());
        return "result";
    }
}
