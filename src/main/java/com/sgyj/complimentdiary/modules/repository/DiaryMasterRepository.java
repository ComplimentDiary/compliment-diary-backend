package com.sgyj.complimentdiary.modules.repository;

import com.sgyj.complimentdiary.modules.repository.entity.UserDiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryMasterRepository extends JpaRepository<UserDiary, Long> {

    Optional<UserDiary> findByUserIdAndDate(String userId, String date);

}
