package com.ast.dm.interactor.member.impl;

import com.ast.dm.entity.Member;
import com.ast.dm.gateway.MemberGateway;
import com.ast.dm.interactor.member.GetMembers;
import com.ast.dm.interactor.member.GetMembersResponse;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class MemberGetter implements GetMembers {
    private final MemberGateway memberGateway;

    @Inject
    public MemberGetter(MemberGateway memberGateway) {
        this.memberGateway = memberGateway;
    }

    @Override public GetMembersResponse execute() {
        List<Member> members = memberGateway.findAll();
        List<GetMembersResponse.MemberItem> memberItems = members
                .stream()
                .map(member -> new GetMembersResponse.MemberItem(member.id, member.username, member.name, member.role))
                .collect(Collectors.toList());
        return new GetMembersResponse(memberItems);
    }
}
