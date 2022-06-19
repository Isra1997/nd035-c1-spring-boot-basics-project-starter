package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("Select * from NOTES")
    List<Note> getAllNoes();

    @Select("Select * From NOTES where noteId=#{noteId}")
    Note getNoteWithId(Integer noteId);

    @Update("Update Note set  noteTitle=#{noteTitle} ,noteDescription=#{noteDescription} where noteId=#{noteId}")
    void updateNote(Note note);

    @Insert("Insert into NOTES()")

    @Delete("Delete from NOTES where noteId=#{noteId}")
    void deleteNote(Integer noteId);
}
