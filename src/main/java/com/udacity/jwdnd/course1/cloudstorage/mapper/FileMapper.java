package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE userid=#{userid}")
    ArrayList<File> getFiles(int userid);

    @Select("SELECT * FROM FILES WHERE fileId=#{fileId} AND userid=#{userid}")
    File getFile(int fileId, int userid);

    @Select("SELECT * FROM FILES WHERE filename=#{filename}")
    File getFilename(String filename);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata)" +
            "VALUES(#{filename}, #{contenttype}, #{filesize}, #{userid}, #{filedata})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Delete("DELETE FROM FILES WHERE fileId=#{fileId}")
    void delete(int fileId);
}
