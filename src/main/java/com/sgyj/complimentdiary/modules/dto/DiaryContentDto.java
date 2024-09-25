package com.sgyj.complimentdiary.modules.dto;

import com.sgyj.complimentdiary.modules.repository.entity.DiaryEntries;
import com.sgyj.complimentdiary.modules.repository.entity.enums.DiaryContentType;
import lombok.Data;

import java.util.List;

@Data
public class DiaryContentDto {

    private DiaryContentType contentType;

    private String content;

    private String image;

    private int rating;

    public DiaryContentDto(DiaryEntries diaryEntries) {
        this.contentType = diaryEntries.getContentType();
        this.content = diaryEntries.getContent();
        this.image = diaryEntries.getImage();
        this.rating = diaryEntries.getRating();
    }

    public static List<DiaryContentDto> from(List<DiaryEntries> diaryEntriesList) {
        return diaryEntriesList.stream().map(DiaryContentDto::from).toList();
    }

    private static DiaryContentDto from(DiaryEntries diaryEntries) {
        return new DiaryContentDto(diaryEntries);
    }

}
