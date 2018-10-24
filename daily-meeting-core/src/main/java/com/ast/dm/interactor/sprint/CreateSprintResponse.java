package com.ast.dm.interactor.sprint;

import java.util.Date;

public class CreateSprintResponse {
    public final long id;
    public final Date startDate;
    public final Date endDate;

    public CreateSprintResponse(long id, Date startDate, Date endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
