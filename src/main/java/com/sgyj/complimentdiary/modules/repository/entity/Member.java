package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    private String userId;

    private String username;

    private String password;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<DiaryMaster> diaryMasterList = new ArrayList<>();

    public Member(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public static Member from(String userId, String username, String password) {
        return new Member(userId, username, password);
    }

}
