package com.sgyj.complimentdiary.modules.dto;

import lombok.Data;

import java.util.List;

@Data
public class DiaryContentResultDto {

    private String date;

    private List<DiaryContentDto> diaryContentList;

    private List<FileDto> fileList;

    public DiaryContentResultDto(List<DiaryContentDto> diaryContentDtoList, List<FileDto> fileDtoList) {
        this.diaryContentList = diaryContentDtoList;
        this.fileList = fileDtoList;
    }

    public static DiaryContentResultDto of(List<DiaryContentDto> diaryContentDtoList, List<FileDto> fileDtoList) {
        return new DiaryContentResultDto(diaryContentDtoList, fileDtoList);
    }

}
