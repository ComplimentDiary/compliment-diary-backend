package com.sgyj.complimentdiary.modules.repository;

import com.sgyj.complimentdiary.modules.repository.entity.DiaryMaster;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryMasterRepository extends JpaRepository<DiaryMaster, Long> {

    Optional<DiaryMaster> findByUserIdAndDate(String userId, String date);

}
