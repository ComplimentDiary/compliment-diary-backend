package com.sgyj.complimentdiary.modules.dto;

import com.sgyj.complimentdiary.modules.repository.entity.enums.DiaryContentType;
import lombok.Data;

/**
 * 일기 내용 결과 dto
 *
 * @author yeji.cho
 * @since 2024.10.02
 */
@Data
public class DiaryContentResultDto {

    private DiaryContentType contentType;

    private String content;

    private String image;

}
