package com.ast.dm.entity;

public class Topic {
    public final long id;
    public final Member member;
    public final String description;

    public Topic(long id, Member member, String description) {
        this.id = id;
        this.member = member;
        this.description = description;
    }
}
