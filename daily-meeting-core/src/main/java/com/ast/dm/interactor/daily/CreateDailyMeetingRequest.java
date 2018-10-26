package com.ast.dm.interactor.daily;

import java.util.Date;
import java.util.List;

public class CreateDailyMeetingRequest {
    public final Date date;
    public final long sprintId;
    public final List<MemberTopic> topics;

    public static class MemberTopic {
        public final long memberId;
        public final String topicValue;

        public MemberTopic(long memberId, String topicValue) {
            this.memberId = memberId;
            this.topicValue = topicValue;
        }

        @Override public String toString() {
            final StringBuilder sb = new StringBuilder("MemberTopic{");
            sb.append("memberId=").append(memberId);
            sb.append(", topicValue='").append(topicValue).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    public CreateDailyMeetingRequest(Date date, long sprintId, List<MemberTopic> topics) {
        this.date = date;
        this.sprintId = sprintId;
        this.topics = topics;
    }
}
