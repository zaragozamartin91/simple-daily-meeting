package com.ast.dm.model.dmeeting;

import com.ast.dm.model.sprint.SprintModel;
import com.ast.dm.model.topic.TopicModel;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "dmeeting")
public class DailyMeetingModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /* Si borro un dailyMeeting, entonces borro sus topics asociados */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "dmeeting")
    private Set<TopicModel> topics = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sprint_id")
    private SprintModel sprint;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<TopicModel> getTopics() {
        return topics;
    }

    public void setTopics(Set<TopicModel> topics) {
        this.topics = topics;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SprintModel getSprint() {
        return sprint;
    }

    public void setSprint(SprintModel sprint) {
        this.sprint = sprint;
    }
}
