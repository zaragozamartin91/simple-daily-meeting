package com.ast.dm.model.sprint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SprintRepository extends JpaRepository<SprintModel, Long> {
}
