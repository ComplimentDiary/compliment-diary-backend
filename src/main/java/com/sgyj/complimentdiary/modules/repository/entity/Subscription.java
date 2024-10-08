package com.sgyj.complimentdiary.modules.repository.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "subscription")
public class Subscription extends UpdatedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String subscriptionType;

    private String status;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
