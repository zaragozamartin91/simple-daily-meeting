package com.ast.dm.entity;

import com.ast.dm.entity.sprint.Sprint;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyMeeting {
    public final long id;
    public final Date date;
    public final Sprint sprint;
    public final List<Topic> topics;

    public DailyMeeting(long id, Date date, Sprint sprint, List<Topic> topics) {
        this.id = id;
        this.date = date;
        this.sprint = sprint;
        this.topics = new ArrayList<>(topics);
    }
}
