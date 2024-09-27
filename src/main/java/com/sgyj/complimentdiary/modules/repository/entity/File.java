package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class File {

    @Id
    @GeneratedValue
    private Long id;

    private Long userDiaryId;

    private String imageUrl;

    @UpdateTimestamp
    private LocalDateTime uploadedAt;

    public File(Long userDiaryId, String imageUrl) {
        this.userDiaryId = userDiaryId;
        this.imageUrl = imageUrl;
    }

    public static File of(Long userDiaryId, String imageUrl) {
        return new File(userDiaryId, imageUrl);
    }

}
