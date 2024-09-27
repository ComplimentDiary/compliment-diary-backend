package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class UserDiary {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private String diaryDate;

    @OneToMany
    private List<Diary> diaryList = new ArrayList<>();

    public UserDiary(User user, String diaryDate) {
        this.user = user;
        this.diaryDate = diaryDate;
    }

    public static UserDiary from(User user, String diaryDate) {
        return new UserDiary(user, diaryDate);
    }

}
