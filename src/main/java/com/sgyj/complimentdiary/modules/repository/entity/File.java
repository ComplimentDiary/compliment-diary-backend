package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Entity
public class File {

    @Id
    @GeneratedValue
    private Long id;

    private Long diaryId;

    private String imageUrl;

    @UpdateTimestamp
    private LocalDateTime uploadedAt;

    public File(Long diaryId, String imageUrl) {
        this.diaryId = diaryId;
        this.imageUrl = imageUrl;
    }

    public static File of(Long diaryId, String imageUrl) {
        return new File(diaryId, imageUrl);
    }

}
