package com.ast.dm.interactor.sprint;

import com.ast.dm.entity.sprint.InvalidSprintException;

public interface CreateSprint {
    CreateSprintResponse execute(CreateSprintRequest request) throws InvalidSprintException;
}
