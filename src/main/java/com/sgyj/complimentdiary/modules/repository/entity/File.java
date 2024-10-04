package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "file")
public class File extends UpdatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long userDiaryId;

    private String imageUrl;

    @CreatedDate
    private LocalDateTime uploadedAt;

    public File(Long userDiaryId, String imageUrl) {
        this.userDiaryId = userDiaryId;
        this.imageUrl = imageUrl;
    }

    public static File of(Long userDiaryId, String imageUrl) {
        return new File(userDiaryId, imageUrl);
    }

}
