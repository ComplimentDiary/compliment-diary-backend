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
public class Diary {

    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private int rating;

    public Diary(String content, int rating) {
        this.content = content;
        this.rating = rating;
    }

    public static Diary from(String content, int rating) {
        return new Diary(content, rating);
    }

}
