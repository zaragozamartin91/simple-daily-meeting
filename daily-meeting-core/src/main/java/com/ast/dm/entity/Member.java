package com.ast.dm.entity;

import java.util.HashMap;
import java.util.Map;

public class Member {
    public final long id;
    public final String username;
    public final String name;
    public final String role;

    public Member(long id, String username, String name, String role) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
    }

    public Map<String, Object> getData() {
        return new HashMap<String, Object>() {{
            put("id", id);
            put("username", username);
            put("name", name);
            put("role", role);
        }};
    }
}
