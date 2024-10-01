package com.sgyj.complimentdiary.modules.dto;

import com.sgyj.complimentdiary.modules.repository.entity.enums.DiaryContentType;
import lombok.Data;

@Data
public class DiaryContentResultDto {

    private DiaryContentType contentType;

    private String content;

    private String image;

}
