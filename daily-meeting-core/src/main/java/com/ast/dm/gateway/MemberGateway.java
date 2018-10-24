package com.ast.dm.gateway;

import com.ast.dm.entity.Member;

import java.util.List;

public interface MemberGateway {
    Member find(String username);

    List<Member> findAll();
}
