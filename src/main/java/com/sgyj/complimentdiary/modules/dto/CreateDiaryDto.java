package com.sgyj.complimentdiary.modules.dto;

import lombok.Data;

import java.util.List;

/**
 * 일기 등록 DTO
 * 일기 등록 요청을 받을 때 사용
 *
 * @author yeji.cho
 * @since 2024.10.02
 */
@Data
public class CreateDiaryDto {

    private String userId;

    private String date;

    List<DiaryContentDto> diaryContentList;

    List<String> imageUrlList;

}
