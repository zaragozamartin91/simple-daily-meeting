package com.ast.dm.model.topic;

import com.ast.dm.model.dmeeting.DailyMeetingModel;
import com.ast.dm.model.member.MemberModel;

import javax.persistence.*;

@Entity
@Table(name = "topic")
public class TopicModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberModel member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dmeeting_id")
    private DailyMeetingModel dmeeting;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MemberModel getMember() {
        return member;
    }

    public void setMember(MemberModel member) {
        this.member = member;
    }

    public DailyMeetingModel getDmeeting() {
        return dmeeting;
    }

    public void setDmeeting(DailyMeetingModel dmeeting) {
        this.dmeeting = dmeeting;
    }
}
