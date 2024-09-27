package com.sgyj.complimentdiary.modules.dto;

import com.sgyj.complimentdiary.modules.repository.entity.Diary;
import com.sgyj.complimentdiary.modules.repository.entity.UserDiary;
import lombok.Data;

import java.util.List;

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

    public static DiaryResultDto from(UserDiary userDiary) {
        return new DiaryResultDto(userDiary.getUser().getUserId(), userDiary.getDiaryDate(), userDiary.getDiaryList());
    }

}
