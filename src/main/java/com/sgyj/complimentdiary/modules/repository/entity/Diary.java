package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "member_diary_id")
    private MemberDiary memberDiary;

    public Diary(String content, int rating, MemberDiary memberDiary) {
        this.content = content;
        this.rating = rating;
        this.memberDiary = memberDiary;
    }

    public static Diary from(String content, int rating, MemberDiary memberDiary) {
        return new Diary(content, rating, memberDiary);
    }

}
