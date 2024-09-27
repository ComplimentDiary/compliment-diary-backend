package com.sgyj.complimentdiary.modules.repository;

import com.sgyj.complimentdiary.modules.repository.entity.User;
import com.sgyj.complimentdiary.modules.repository.entity.UserDiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDiaryRepository extends JpaRepository<UserDiary, Long> {

    Optional<UserDiary> findByUserAndDiaryDate(User user, String diaryDate);

}
