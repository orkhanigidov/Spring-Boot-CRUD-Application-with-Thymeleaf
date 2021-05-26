package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class NoteService {
    private final NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public ArrayList<Note> getNotes(int userid) {
        return noteMapper.getNotes(userid);
    }

    public int insert(Note note) {
        return noteMapper.insert(note);
    }

    public void update(Note note) {
        noteMapper.update(note);
    }

    public void delete(int noteid) {
        noteMapper.delete(noteid);
    }
}
