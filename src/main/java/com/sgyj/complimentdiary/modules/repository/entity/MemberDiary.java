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
@Table(name = "member_diary")
public class MemberDiary {

    @Id
    @Column(name = "member_diary_id")
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    private String diaryDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "memberDiary")
    private List<Diary> diaryList = new ArrayList<>();

    public MemberDiary(Member member, String diaryDate) {
        this.member = member;
        this.diaryDate = diaryDate;
    }

    public static MemberDiary from(Member member, String diaryDate) {
        return new MemberDiary(member, diaryDate);
    }

}
