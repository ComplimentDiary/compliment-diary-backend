package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Embeddable
public class DiaryMasterPk implements Serializable {

    private String date;

    private String userId;

}
