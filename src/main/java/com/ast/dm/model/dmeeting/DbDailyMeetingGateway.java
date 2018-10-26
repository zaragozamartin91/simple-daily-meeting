package com.ast.dm.model.dmeeting;

import com.ast.dm.entity.DailyMeeting;
import com.ast.dm.entity.Topic;
import com.ast.dm.gateway.DailyMeetingGateway;
import com.ast.dm.model.member.MemberModel;
import com.ast.dm.model.member.MemberRepository;
import com.ast.dm.model.sprint.SprintModel;
import com.ast.dm.model.sprint.SprintRepository;
import com.ast.dm.model.topic.TopicModel;
import com.ast.dm.model.topic.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DbDailyMeetingGateway implements DailyMeetingGateway {
    private final DailyMeetingRepository dailyMeetingRepository;
    private final SprintRepository sprintRepository;
    private final MemberRepository memberRepository;
    private final TopicRepository topicRepository;

    @Autowired
    public DbDailyMeetingGateway(
            DailyMeetingRepository dailyMeetingRepository,
            SprintRepository sprintRepository,
            MemberRepository memberRepository,
            TopicRepository topicRepository) {
        this.dailyMeetingRepository = dailyMeetingRepository;
        this.sprintRepository = sprintRepository;
        this.memberRepository = memberRepository;
        this.topicRepository = topicRepository;
    }

    @Transactional
    @Override public DailyMeeting save(DailyMeeting dailyMeeting) {
        DailyMeetingModel dailyMeetingModel = new DailyMeetingModel();
        dailyMeetingModel.setDate(dailyMeeting.date);
        Optional<SprintModel> sprintModel = sprintRepository.findById(dailyMeeting.sprint.id);
        dailyMeetingModel.setSprint(sprintModel.get());

        DailyMeetingModel savedDailyMeeting = dailyMeetingRepository.save(dailyMeetingModel);

        List<Topic> topics = dailyMeeting.topics.stream().map(t -> {
            TopicModel tm = new TopicModel();
            tm.setDescription(t.description);
            tm.setDmeeting(savedDailyMeeting);
            Optional<MemberModel> memberModel = memberRepository.findById(t.member.id);
            tm.setMember(memberModel.get());
            TopicModel topicModel = topicRepository.save(tm);

            return new Topic(topicModel.getId(), t.member, t.description);
        }).collect(Collectors.toList());

        return new DailyMeeting(savedDailyMeeting.getId(), dailyMeeting.date, dailyMeeting.sprint, topics);
    }
}
