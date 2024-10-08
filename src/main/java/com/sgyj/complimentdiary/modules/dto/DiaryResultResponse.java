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

    private String memberId;

    private String date;

    List<DiaryContent> diaryContentList;

    public DiaryResultResponse(String memberId, String date, List<Diary> diaryList) {
        this.memberId = memberId;
        this.date = date;
        this.diaryContentList = DiaryContent.from(diaryList);
    }

    public static DiaryResultResponse from(MemberDiary memberDiary) {
        return new DiaryResultResponse(memberDiary.getMemberId(), memberDiary.getDiaryDate(), memberDiary.getDiaryList());
    }

}
