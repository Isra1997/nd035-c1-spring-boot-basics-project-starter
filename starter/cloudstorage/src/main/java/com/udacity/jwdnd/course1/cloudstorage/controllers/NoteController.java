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
    void getAllNotes(@ModelAttribute("noteData")Note note,Model model){
        model.addAttribute("notes", noteService.getAllNotes());
    }


    @PostMapping
    String createOrUpdateNote(@ModelAttribute("noteData")Note note, Model model){
        int created = 0;
        //check if the note is created before or not
        if(note.getNoteId()==null){
             created = noteService.createNote(note);
        }else{
            created = 1;
            noteService.updateNote(note);
        }
        //display success message
        if(created == 0){
            model.addAttribute("message","Opps..something went wrong please try again later!");
            model.addAttribute("display",false);
        }else{
            model.addAttribute("display" ,true);
        }
        //update notes
        model.addAttribute("notes", noteService.getAllNotes());
        return "result";
    }


    @GetMapping(value = "/{noteId}")
    String deleteNote(@PathVariable Integer noteId,@ModelAttribute("noteData")Note note,Model model){
        //delete note
        noteService.deleteNote(noteId);
        //check if the delete operation was successful
        if(noteService.isNotePresent(note.getNoteId())){
            model.addAttribute("display", true);
        }else{
            model.addAttribute("message","Something went wrong please try again!");
            model.addAttribute("display", false);
        }
        //set the updated notes
        model.addAttribute("notes", noteService.getAllNotes());
        return "result";
    }

}
