package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
public class File {

    @Id
    @GeneratedValue
    private Long id;

    private Long diaryId;

    private String imageUrl;

    private LocalDateTime uploadedAt;
    
}
