package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Subscription extends UpdatedEntity {

    @Id @GeneratedValue
    private Long id;

    private String subscriptionType;

    private String status;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
