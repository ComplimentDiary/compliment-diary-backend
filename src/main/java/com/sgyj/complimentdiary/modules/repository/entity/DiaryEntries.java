package com.sgyj.complimentdiary.modules.repository.entity;

import com.sgyj.complimentdiary.modules.repository.entity.enums.DiaryContentType;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class DiaryEntries {

    @Id @GeneratedValue
    private Long id;

    private String content;

    @Enumerated(value = EnumType.STRING)
    private DiaryContentType contentType;

    private int rating;

    @ManyToOne
    @JoinColumn(name = "DIARY_MASTER_DATE", referencedColumnName = "date")
    @JoinColumn(name = "DIARY_MASTER_USERID", referencedColumnName = "userid")
    private DiaryMaster diaryMaster;

}
