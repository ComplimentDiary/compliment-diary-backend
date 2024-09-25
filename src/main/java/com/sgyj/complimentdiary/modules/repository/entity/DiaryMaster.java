package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class DiaryMaster {

    @Id @GeneratedValue
    private Long id;

    private String userId;

    private String date;

    @OneToMany(mappedBy = "diaryMaster", cascade = CascadeType.ALL)
    private List<DiaryEntries> diaryEntriesList;

    @ManyToOne @JoinColumn(name = "member_user_id")
    private Member member;

    public DiaryMaster(String userId, String date, List<DiaryEntries> diaryEntryList) {
        this.userId = userId;
        this.date = date;
        this.diaryEntriesList = diaryEntryList;
    }

    public static DiaryMaster from(String userId, String date, List<DiaryEntries> diaryEntryList) {
        return new DiaryMaster(userId, date, diaryEntryList);
    }

}
