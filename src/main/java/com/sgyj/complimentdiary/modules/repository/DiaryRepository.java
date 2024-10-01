package com.sgyj.complimentdiary.modules.repository;

import com.sgyj.complimentdiary.modules.repository.entity.Diary;
import com.sgyj.complimentdiary.modules.repository.entity.UserDiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    List<Diary> findByUserDiary(UserDiary userDiary);

}
