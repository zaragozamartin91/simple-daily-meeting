package com.ast.dm.model.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberModel, Long> {
    MemberModel findOneByUsername(String username);
}
