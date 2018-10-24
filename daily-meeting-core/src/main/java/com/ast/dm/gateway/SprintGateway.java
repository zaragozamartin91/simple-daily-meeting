package com.ast.dm.gateway;

import com.ast.dm.entity.sprint.Sprint;

import java.util.List;

public interface SprintGateway {
    Sprint save(Sprint sprint);

    List<Sprint> find();

    List<Sprint> findNonExpired();
}
