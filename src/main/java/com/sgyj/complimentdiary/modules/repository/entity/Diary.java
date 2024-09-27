package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Diary {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "user_diary_id")
    private UserDiary userDiary;

    public Diary(String content, int rating, UserDiary userDiary) {
        this.content = content;
        this.rating = rating;
        this.userDiary = userDiary;
    }

    public static Diary from(String content, int rating, UserDiary userDiary) {
        return new Diary(content, rating, userDiary);
    }

}
