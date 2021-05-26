package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {
    private final UserService userService;
    private final NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping("/notes")
    public String createAndUpdateNote(@ModelAttribute Note note, Authentication authentication, Model model) {
        int userid = userService.getUser(authentication.getName()).getUserid();
        Integer noteid = note.getNoteid();
        String notetitle = note.getNotetitle();
        String notedescription = note.getNotedescription();
        if (noteid == null) {
            noteService.insert(new Note(null, notetitle, notedescription, userid));
        } else {
            noteService.update(new Note(noteid, notetitle, notedescription, userid));
        }
        model.addAttribute("success", true);
        return "result";
    }

    @GetMapping("/notes/delete/{noteid}")
    public String deleteNote(@PathVariable int noteid, Model model) {
        noteService.delete(noteid);
        model.addAttribute("success", true);
        return "result";
    }
}
