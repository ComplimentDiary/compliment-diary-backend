package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class UserDiary {

    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    private Long diaryId;

    @OneToMany(mappedBy = "diaryMaster", cascade = CascadeType.ALL)
    private List<Diary> diaryList;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    public UserDiary(String userId, Long diaryId, List<Diary> diaryEntryList) {
        this.userId = userId;
        this.diaryId = diaryId;
        this.diaryList = diaryEntryList;
    }

    public static UserDiary from(String userId, Long diaryId, List<Diary> diaryEntryList) {
        return new UserDiary(userId, diaryId, diaryEntryList);
    }

}
