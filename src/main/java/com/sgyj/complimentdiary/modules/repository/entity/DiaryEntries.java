package com.sgyj.complimentdiary.modules.repository.entity;

import com.sgyj.complimentdiary.modules.repository.entity.enums.DiaryContentType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class DiaryEntries {

    @Id @GeneratedValue
    private Long id;

    private String content;

    @Enumerated(value = EnumType.STRING)
    private DiaryContentType contentType;

    private String image;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "DIARY_MASTER_DATE", referencedColumnName = "date")
    @JoinColumn(name = "DIARY_MASTER_USERID", referencedColumnName = "userid")
    private DiaryMaster diaryMaster;

    public DiaryEntries(String content, DiaryContentType diaryContentType, int rating) {
        this.content = content;
        this.contentType = diaryContentType;
        this.rating = rating;
    }

    public static DiaryEntries from(String content, DiaryContentType diaryContentType, int rating) {
        return new DiaryEntries(content, diaryContentType, rating);
    }

}
