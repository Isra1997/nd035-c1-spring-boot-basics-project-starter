package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {

    @Select("Select * from NOTES")
    List<Note> getAllNotes();


    @Select("Select * From NOTES where noteId=#{noteId}")
    Note getNoteWithId(Integer noteId);

    @Update("Update NOTES set  noteTitle=#{noteTitle} ,noteDescription=#{noteDescription} where noteId=#{noteId}")
    void updateNote(Note note);

    @Insert("Insert into NOTES(noteTitle,noteDescription,userId) Values (#{noteTitle},#{noteDescription},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int createNote(Note note);

    @Delete("Delete from NOTES where noteId=#{noteId}")
    void deleteNote(Integer noteId);
}
