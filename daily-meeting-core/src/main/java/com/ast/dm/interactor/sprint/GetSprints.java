package com.ast.dm.interactor.sprint;

public interface GetSprints {
    GetSprintsResponse find();

    GetSprintsResponse findNonExpired();
}
