package com.sgyj.complimentdiary.modules.dto;

import com.sgyj.complimentdiary.modules.repository.entity.Diary;
import com.sgyj.complimentdiary.modules.repository.entity.MemberDiary;
import lombok.Data;

import java.util.List;

/**
 * 일기 결과 dto
 *
 * @since 2024.10.02
 */
@Data
public class DiaryResultDto {

    private String userId;

    private String date;

    List<DiaryContentDto> diaryContentList;

    public DiaryResultDto(String userId, String date, List<Diary> diaryList) {
        this.userId = userId;
        this.date = date;
        this.diaryContentList = DiaryContentDto.from(diaryList);
    }

    public static DiaryResultDto from(MemberDiary memberDiary) {
        return new DiaryResultDto(memberDiary.getMember().getMemberId(), memberDiary.getDiaryDate(), memberDiary.getDiaryList());
    }

}
