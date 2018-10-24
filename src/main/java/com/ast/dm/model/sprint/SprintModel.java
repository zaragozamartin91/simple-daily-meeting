package com.ast.dm.model.sprint;

import com.ast.dm.model.dmeeting.DailyMeetingModel;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sprint")
public class SprintModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", nullable = true)
    private Date endDate;

    /* Si elimino un sprint entonces se eliminan las daily meetings asociadas */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "sprint")
    private Set<DailyMeetingModel> dmeetings = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<DailyMeetingModel> getDmeetings() {
        return dmeetings;
    }

    public void setDmeetings(Set<DailyMeetingModel> dmeetings) {
        this.dmeetings = dmeetings;
    }
}
