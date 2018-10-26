package com.ast.dm.interactor.daily;

import com.ast.dm.entity.Topic;
import com.ast.dm.entity.sprint.Sprint;

import java.util.Date;
import java.util.List;

public class CreateDailyMeetingResponse {
    public final long id;
    public final Date date;
    public final Sprint sprint;
    public final List<Topic> topics;

    public CreateDailyMeetingResponse(long id, Date date, Sprint sprint, List<Topic> topics) {
        this.id = id;
        this.date = date;
        this.sprint = sprint;
        this.topics = topics;
    }
}
