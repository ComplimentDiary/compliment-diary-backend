package com.sgyj.complimentdiary.modules.repository;

import com.sgyj.complimentdiary.modules.repository.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {

    List<File> findByUserDiaryId(Long userDiaryId);

}
