package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.Mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.Mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    NoteMapper noteMapper;
    UserMapper userMapper;

    public NoteService(NoteMapper noteMapper,UserMapper userMapper) {
        this.noteMapper = noteMapper;
        this.userMapper = userMapper;
    }

    public List<Note> getAllNotes(){
        return noteMapper.getAllNotes();
    }

    public int createNote(Note note){
        User user = userMapper.getUser(getUsername());
        note.setUserId(user.getUserId());
        return noteMapper.createNote(note);
    }

    public void deleteNote(Integer noteId){
        noteMapper.deleteNote(noteId);
    }

    public void updateNote(Note note){
        noteMapper.updateNote(note);
    }

    public boolean isNotePresent(Integer noteId){
        return noteMapper.getNoteWithId(noteId) == null;
    }



    //get the current user's username
    public String getUsername(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails)principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
