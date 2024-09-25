package com.sgyj.complimentdiary.modules.dto;

import com.sgyj.complimentdiary.modules.repository.entity.DiaryEntries;
import com.sgyj.complimentdiary.modules.repository.entity.DiaryMaster;
import lombok.Data;

import java.util.List;

@Data
public class DiaryResultDto {

    private String userId;

    private String date;

    List<DiaryContentDto> diaryContentList;

    public DiaryResultDto(String userId, String date, List<DiaryEntries> diaryEntriesList) {
        this.userId = userId;
        this.date = date;
        this.diaryContentList = DiaryContentDto.from(diaryEntriesList);
    }

    public static DiaryResultDto from(DiaryMaster diaryMaster) {
        return new DiaryResultDto(diaryMaster.getUserId(), diaryMaster.getDate(), diaryMaster.getDiaryEntriesList());
    }

}
