package com.sgyj.complimentdiary.modules.repository;

import com.sgyj.complimentdiary.modules.repository.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {


}

