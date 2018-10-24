package com.ast.dm.interactor.member;

import java.util.List;

public class GetMembersResponse {
    public GetMembersResponse(List<MemberItem> memberItems) {
        this.memberItems = memberItems;
    }

    public static class MemberItem {
        public final long id;
        public final String username;
        public final String name;
        public final String role;

        public MemberItem(long id, String username, String name, String role) {
            this.id = id;
            this.username = username;
            this.name = name;
            this.role = role;
        }
    }

    public final List<MemberItem> memberItems;
}
