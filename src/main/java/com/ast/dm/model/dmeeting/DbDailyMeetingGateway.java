package com.ast.dm.model.dmeeting;

import com.ast.dm.entity.DailyMeeting;
import com.ast.dm.gateway.DailyMeetingGateway;
import com.ast.dm.model.sprint.SprintModel;
import com.ast.dm.model.sprint.SprintRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DbDailyMeetingGateway implements DailyMeetingGateway {
    private final DailyMeetingRepository dailyMeetingRepository;
    private final SprintRepository sprintRepository;

    @Autowired
    public DbDailyMeetingGateway(DailyMeetingRepository dailyMeetingRepository, SprintRepository sprintRepository) {
        this.dailyMeetingRepository = dailyMeetingRepository;
        this.sprintRepository = sprintRepository;
    }

    @Transactional
    @Override public DailyMeeting save(DailyMeeting dailyMeeting) {
        DailyMeetingModel dailyMeetingModel = new DailyMeetingModel();
        dailyMeetingModel.setDate(dailyMeeting.date);
        Optional<SprintModel> sprintModel = sprintRepository.findById(dailyMeeting.sprint.id);
        dailyMeetingModel.setSprint(sprintModel.get());
        return null;
    }
}
