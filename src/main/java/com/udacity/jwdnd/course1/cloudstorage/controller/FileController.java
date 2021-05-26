package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {
    private final UserService userService;
    private final FileService fileService;

    public FileController(FileService fileService, UserService userService) {
        this.userService = userService;
        this.fileService = fileService;
    }

    @PostMapping("/files")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile multipartFile, Authentication authentication, Model model) throws IOException {
        String fileError = null;
        String filename = multipartFile.getOriginalFilename();
        String contenttype = multipartFile.getContentType();
        long filesize = multipartFile.getSize();
        int userid = userService.getUser(authentication.getName()).getUserid();
        byte[] filedata = multipartFile.getBytes();
        if (multipartFile.getSize() == 0) {
            fileError = "No file selected.";
        }
        if (!fileService.isFileAvailable(filename)) {
            fileError = "The file already exists.";
        }
        if (fileError == null) {
            int rowsAdded = fileService.insert(new File(null, filename, contenttype, filesize, userid, filedata));
            if (rowsAdded < 0) {
                fileError = "There was an error uploading file. Please try again.";
            }
        }
        if (fileError == null) {
            model.addAttribute("success", true);
        } else {
            model.addAttribute("success", false);
            model.addAttribute("error", fileError);
        }
        return "result";
    }

    @GetMapping("/files/view/{fileId}")
    public ResponseEntity<ByteArrayResource> viewFile(@PathVariable int fileId, Authentication authentication) {
        int userid = userService.getUser(authentication.getName()).getUserid();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(fileService.getFile(fileId, userid).getContenttype()))
                .body(new ByteArrayResource(fileService.getFile(fileId, userid).getFiledata()));
    }

    @GetMapping("/files/delete/{fileId}")
    public String deleteFile(@PathVariable int fileId, Model model) {
        fileService.delete(fileId);
        model.addAttribute("success", true);
        return "result";
    }
}
