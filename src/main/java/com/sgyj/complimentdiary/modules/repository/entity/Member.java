package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "member")
public class Member extends UpdatedEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String memberId;

    private String memberName;

    private String email;

    private String password;

    @OneToOne(mappedBy = "member")
    private Setting setting;

    @OneToOne(mappedBy = "member")
    private Subscription subscription;

    @OneToMany(mappedBy = "member")
    private List<MemberDiary> memberDiaryList = new ArrayList<>();

    public Member(String memberId, String memberName, String password, String email) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.password = password;
        this.email = email;
    }

    public static Member of(String memberId, String memberName, String password, String email) {
        return new Member(memberId, memberName, password, email);
    }

}
