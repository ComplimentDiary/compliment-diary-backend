package com.sgyj.complimentdiary.modules.repository;

import com.sgyj.complimentdiary.modules.repository.entity.Member;
import com.sgyj.complimentdiary.modules.repository.entity.MemberDiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberDiaryRepository extends JpaRepository<MemberDiary, Long> {

    Optional<MemberDiary> findByMemberAndDiaryDate(Member member, String diaryDate);

}
