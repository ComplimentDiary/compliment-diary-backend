package com.sgyj.complimentdiary.modules.dto;

import com.sgyj.complimentdiary.modules.repository.entity.Diary;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 일기 내용 dto
 *
 * @author yeji.cho
 * @since 2024.10.02
 */
@NoArgsConstructor
@Data
public class DiaryContentDto {

    private String content;

    private int rating;

    public DiaryContentDto(Diary diary) {
        this.content = diary.getContent();
        this.rating = diary.getRating();
    }

    public static List<DiaryContentDto> from(List<Diary> diaryList) {
        return diaryList.stream().map(DiaryContentDto::from).toList();
    }

    private static DiaryContentDto from(Diary diary) {
        return new DiaryContentDto(diary);
    }

}
