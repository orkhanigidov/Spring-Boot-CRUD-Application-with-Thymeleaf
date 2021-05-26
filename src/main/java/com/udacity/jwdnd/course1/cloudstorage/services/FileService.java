package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FileService {
    private final FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public ArrayList<File> getFiles(int userid) {
        return fileMapper.getFiles(userid);
    }

    public boolean isFileAvailable(String filename) {
        return fileMapper.getFilename(filename) == null;
    }

    public File getFile(int fileId, int userid) {
        return fileMapper.getFile(fileId, userid);
    }

    public int insert(File file) {
        return fileMapper.insert(file);
    }

    public void delete(int fileId) {
        fileMapper.delete(fileId);
    }
}
