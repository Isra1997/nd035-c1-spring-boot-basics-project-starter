package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/notes")
public class NoteController {
    NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping
    String getAllNotes(@ModelAttribute("noteData")Note note,Model model){
        System.out.println("hello");
        model.addAttribute("results", noteService.getAllNotes());
        model.addAttribute("activeTab","/notes");
        return "home";
    }


    @PostMapping
    String postNote(@ModelAttribute("noteData")Note note,Model model){
        //check if the note is created before or not
        if(note.getNoteId()==null){
            noteService.createNote(note);
        }else{
            noteService.updateNote(note);
        }
        model.addAttribute("results", noteService.getAllNotes());
        model.addAttribute("activeTab","/notes");
        return "home";
    }


    @GetMapping(value = "{noteId}")
    String deleteNote(@PathVariable Integer noteId,@ModelAttribute("noteData")Note note,Model model){
        noteService.deleteNote(noteId);
        model.addAttribute("results", noteService.getAllNotes());
        model.addAttribute("activeTab","/notes");
        return "home";
    }

}
