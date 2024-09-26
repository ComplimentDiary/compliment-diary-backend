package com.sgyj.complimentdiary.modules.dto;

import lombok.Data;

import java.util.List;

@Data
public class CreateDiaryDto {

    private String userId;

    private String date;

    List<DiaryContentDto> diaryContentList;

    List<String> imageUrlList;

}
