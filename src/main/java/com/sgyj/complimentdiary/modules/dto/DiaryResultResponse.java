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
public class DiaryResultResponse {

    private String userId;

    private String date;

    List<DiaryContent> diaryContentList;

    public DiaryResultResponse(String userId, String date, List<Diary> diaryList) {
        this.userId = userId;
        this.date = date;
        this.diaryContentList = DiaryContent.from(diaryList);
    }

    public static DiaryResultResponse from(MemberDiary memberDiary) {
        return new DiaryResultResponse(memberDiary.getMemberId(), memberDiary.getDiaryDate(), memberDiary.getDiaryList());
    }

}
