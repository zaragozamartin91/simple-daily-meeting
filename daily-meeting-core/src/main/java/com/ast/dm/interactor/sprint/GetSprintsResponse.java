package com.ast.dm.interactor.sprint;

import com.ast.dm.entity.sprint.Sprint;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class GetSprintsResponse {
    public static class SprintItem {
        public final long id;
        public final Date startDate;
        public final Date endDate;
        public final String title;

        public SprintItem(long id, Date startDate, Date endDate, String title) {
            this.id = id;
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
        }

        public SprintItem(Sprint s) {
            this(s.id, s.getStartDate(), s.getEndDate(), s.getTitle());
        }
    }

    private List<SprintItem> sprintItems;

    public GetSprintsResponse(List<SprintItem> sprintItems) {
        this.sprintItems = sprintItems;
    }

    public List<SprintItem> getSprintItems() {
        return Collections.unmodifiableList(sprintItems);
    }
}
