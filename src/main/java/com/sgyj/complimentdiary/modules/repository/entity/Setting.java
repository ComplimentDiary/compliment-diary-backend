package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Setting extends UpdatedEntity {

    @Id
    @GeneratedValue
    private Long id;

    private boolean isNotification;

    private String theme;

    private String language;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
