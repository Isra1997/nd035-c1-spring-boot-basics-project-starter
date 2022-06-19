package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/file")
public class FileController {

    FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public String getAllFiles(Model model){
        model.addAttribute("AllFiles", fileService.getAllFiles());
        return "home";
    }

    @PostMapping
    public String addFile(@ModelAttribute File file,Model model){
        fileService.uploadedFile(file);
        model.addAttribute("AllFiles", fileService.getAllFiles());
        return "home";
    }

    @DeleteMapping(value = "{fileId}")
    public String  deleteFile(@PathVariable Integer fileId,Model model){
        fileService.deleteFile(fileId);
        model.addAttribute("AllFiles",fileService.getAllFiles());
        return  "home";
    }
    
}
