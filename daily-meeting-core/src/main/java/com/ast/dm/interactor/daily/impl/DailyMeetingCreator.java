package com.ast.dm.interactor.daily.impl;

import com.ast.dm.entity.DailyMeeting;
import com.ast.dm.entity.Member;
import com.ast.dm.entity.Topic;
import com.ast.dm.entity.sprint.Sprint;
import com.ast.dm.gateway.DailyMeetingGateway;
import com.ast.dm.interactor.daily.CreateDailyMeeting;
import com.ast.dm.interactor.daily.CreateDailyMeetingRequest;
import com.ast.dm.interactor.daily.CreateDailyMeetingResponse;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class DailyMeetingCreator implements CreateDailyMeeting {
    private final DailyMeetingGateway dailyMeetingGateway;

    @Inject
    public DailyMeetingCreator(DailyMeetingGateway dailyMeetingGateway) {
        this.dailyMeetingGateway = dailyMeetingGateway;
    }

    @Override public CreateDailyMeetingResponse execute(CreateDailyMeetingRequest request) {
        Sprint sprint = new Sprint(request.sprintId);
        List<Topic> topics = request.topics
                .stream()
                .map(mt -> new Topic(new Member(mt.memberId), mt.topicValue))
                .collect(Collectors.toList());

        DailyMeeting dailyMeeting = new DailyMeeting(request.date, sprint, topics);
        DailyMeeting savedDailyMeeting = dailyMeetingGateway.save(dailyMeeting);
        return new CreateDailyMeetingResponse(
                savedDailyMeeting.id,
                savedDailyMeeting.date,
                savedDailyMeeting.sprint,
                savedDailyMeeting.topics
        );
    }
}
