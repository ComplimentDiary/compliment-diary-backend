package com.sgyj.complimentdiary.modules.repository;

import com.sgyj.complimentdiary.modules.repository.entity.DiaryMaster;
import com.sgyj.complimentdiary.modules.repository.entity.DiaryMasterPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryMasterRepository extends JpaRepository<DiaryMaster, DiaryMasterPk> {

}
