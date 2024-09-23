package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class DiaryMaster {

    @EmbeddedId
    private DiaryMasterPk id;

    @OneToMany(mappedBy = "diaryMaster", cascade = CascadeType.ALL)
    private List<DiaryEntries> diaryEntriesList = new ArrayList<>();

    @ManyToOne @JoinColumn(name = "member_user_id")
    private Member member;

}
