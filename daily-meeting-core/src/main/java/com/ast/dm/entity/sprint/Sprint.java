package com.ast.dm.entity.sprint;

import java.util.Date;
import java.util.Optional;

public class Sprint {
    public final long id;
    private Date startDate;
    private Date endDate;

    public Sprint(Date startDate, Date endDate) {
        this(0, startDate, endDate);
    }

    public Sprint(long id, Date startDate, Date endDate) {
        this.id = id;
        this.startDate = Optional.ofNullable(startDate).orElseThrow(() -> new InvalidSprintException("No se asigno una fecha de inicio"));
        this.endDate = Optional.ofNullable(endDate).orElseThrow(() -> new InvalidSprintException("No se asigno una fecha de fin"));

        if (startDate.getTime() > endDate.getTime()) {
            throw new InvalidSprintException("La fecha de fin es superior a la fecha de inicio");
        }
    }

    public Date getStartDate() {
        return new Date(startDate.getTime());
    }

    public Date getEndDate() {
        return new Date(endDate.getTime());
    }
}
