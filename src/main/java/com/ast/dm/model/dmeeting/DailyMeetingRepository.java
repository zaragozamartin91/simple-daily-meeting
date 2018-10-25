package com.ast.dm.model.dmeeting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyMeetingRepository extends JpaRepository<DailyMeetingModel, Long> {
}
