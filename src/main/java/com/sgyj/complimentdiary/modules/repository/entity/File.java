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

    private Long memberDiaryId;

    private String imageUrl;

    @CreatedDate
    private LocalDateTime uploadedAt;

    public File(Long memberDiaryId, String imageUrl) {
        this.memberDiaryId = memberDiaryId;
        this.imageUrl = imageUrl;
    }

    public static File of(Long memberDiaryId, String imageUrl) {
        return new File(memberDiaryId, imageUrl);
    }

}
