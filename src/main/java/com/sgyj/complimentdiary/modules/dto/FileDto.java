package com.sgyj.complimentdiary.modules.dto;

import com.sgyj.complimentdiary.modules.repository.entity.File;
import lombok.Data;

import java.util.List;

@Data
public class FileDto {

    private String imageUrl;

    public FileDto(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    private static FileDto from(File file) {
        return new FileDto(file.getImageUrl());
    }

    public static List<FileDto> from(List<File> fileList) {
        return fileList.stream().map(FileDto::from).toList();
    }

}
