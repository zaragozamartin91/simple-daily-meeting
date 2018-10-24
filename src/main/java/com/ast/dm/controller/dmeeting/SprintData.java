package com.ast.dm.controller.dmeeting;

import java.util.Date;

public class SprintData {
    private long id;
    private Date startDate;
    private Date endDate;
    private String title;

    public SprintData() {
    }

    public SprintData(long id, Date startDate, Date endDate, String title) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
