package com.ast.dm.model.member;

import com.ast.dm.entity.Member;
import com.ast.dm.gateway.MemberGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DbMemberGateway implements MemberGateway {
    private final MemberRepository memberRepository;

    @Autowired
    public DbMemberGateway(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override public Member find(String username) {
        MemberModel memberModel = memberRepository.findOneByUsername(username);
        return new Member(memberModel.getId(), memberModel.getUsername(), memberModel.getName(), memberModel.getRole());
    }

    @Override public List<Member> findAll() {
        return memberRepository
                .findAll()
                .stream()
                .map(m -> new Member(m.getId(), m.getUsername(), m.getName(), m.getRole()))
                .collect(Collectors.toList());
    }
}
