package com.ast.dm.interactor.sprint.impl;

import com.ast.dm.entity.sprint.Sprint;
import com.ast.dm.gateway.SprintGateway;
import com.ast.dm.interactor.sprint.GetSprints;
import com.ast.dm.interactor.sprint.GetSprintsResponse;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class SprintGetter implements GetSprints {
    private final SprintGateway sprintGateway;

    @Inject
    public SprintGetter(SprintGateway sprintGateway) {
        this.sprintGateway = sprintGateway;
    }

    @Override public GetSprintsResponse find() {
        List<Sprint> sprints = sprintGateway.find();
        List<GetSprintsResponse.SprintItem> sprintItems = sprints.stream().map(GetSprintsResponse.SprintItem::new).collect(Collectors.toList());
        return new GetSprintsResponse(sprintItems);
    }

    @Override public GetSprintsResponse findNonExpired() {
        List<Sprint> sprints = sprintGateway.findNonExpired();
        List<GetSprintsResponse.SprintItem> sprintItems = sprints.stream().map(GetSprintsResponse.SprintItem::new).collect(Collectors.toList());
        return new GetSprintsResponse(sprintItems);
    }
}
