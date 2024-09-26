package com.sgyj.complimentdiary.modules.repository.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Setting extends UpdatedAt {

    @Id
    @GeneratedValue
    private Long id;

    private String userId;

    private boolean isNotification;

    private String theme;

    private String language;

}
