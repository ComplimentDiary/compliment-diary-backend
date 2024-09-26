package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class UserDiary {

    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    private Long diaryId;

    public UserDiary(String userId, Long diaryId) {
        this.userId = userId;
        this.diaryId = diaryId;
    }

    public static UserDiary from(String userId, Long diaryId) {
        return new UserDiary(userId, diaryId);
    }

}
