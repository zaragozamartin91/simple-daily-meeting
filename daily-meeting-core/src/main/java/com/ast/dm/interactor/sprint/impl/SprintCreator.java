package com.ast.dm.interactor.sprint.impl;

import com.ast.dm.entity.sprint.InvalidSprintException;
import com.ast.dm.entity.sprint.Sprint;
import com.ast.dm.gateway.SprintGateway;
import com.ast.dm.interactor.sprint.CreateSprint;
import com.ast.dm.interactor.sprint.CreateSprintRequest;
import com.ast.dm.interactor.sprint.CreateSprintResponse;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SprintCreator implements CreateSprint {
    private SprintGateway sprintGateway;

    @Inject
    public SprintCreator(SprintGateway sprintGateway) {
        this.sprintGateway = sprintGateway;
    }

    @Override public CreateSprintResponse execute(CreateSprintRequest request) throws InvalidSprintException {
        Sprint sprint = new Sprint(request.startDate, request.endDate, request.title);
        Sprint newSprint = sprintGateway.save(sprint);
        return new CreateSprintResponse(newSprint.id, newSprint.getStartDate(), newSprint.getEndDate());
    }
}
