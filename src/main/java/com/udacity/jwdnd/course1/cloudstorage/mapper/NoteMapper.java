package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface NoteMapper {
    @Select("SELECT * FROM NOTES WHERE userid=#{userid}")
    ArrayList<Note> getNotes(int userid);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid)" +
            "VALUES(#{notetitle}, #{notedescription}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "noteid")
    int insert(Note note);

    @Update("UPDATE NOTES SET notetitle=#{notetitle}, notedescription=#{notedescription}" +
            "WHERE noteid=#{noteid} AND userid=#{userid}")
    void update(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid=#{noteid}")
    void delete(int noteid);
}
