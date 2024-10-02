package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Setting extends UpdatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean isNotification;

    private String theme;

    private String language;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
