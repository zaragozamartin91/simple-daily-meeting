package com.ast.dm.interactor.sprint;

import java.util.Date;

public class CreateSprintRequest {
    public final Date startDate;
    public final Date endDate;

    public CreateSprintRequest(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
