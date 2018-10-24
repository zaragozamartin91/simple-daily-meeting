package com.ast.dm.interactor.sprint;

import java.util.Date;

public class CreateSprintRequest {
    public final Date startDate;
    public final Date endDate;
    public final String title;

    public CreateSprintRequest(Date startDate, Date endDate, String title) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
    }
}
