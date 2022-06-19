package com.udacity.jwdnd.course1.cloudstorage.Mapper;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface FileMapper {

    @Select("Select * from FILES")
    ArrayList<File> getAllFiles();

    @Select("Select * from FILES where filename=#{filename}")
    File getFileByFilename(String filename);

    @Select("Select * from FILES where fileId=#{fileId}")
    File getFile(Integer fileId);

    @Insert("Insert into FILES(filename, contentType, fileSize ,UserId,fileData ) Values (#{filename},#{contentType},#{fileSize},#{UserId},#{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int createFile(File file);

    @Delete("Delete from FILES where fileId=#{fileId}")
    void deleteFile(Integer fileId);
}
