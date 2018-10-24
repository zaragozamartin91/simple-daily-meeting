package com.ast.dm.model.sprint;

import com.ast.dm.entity.sprint.Sprint;
import com.ast.dm.gateway.SprintGateway;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class DbSprintGateway implements SprintGateway {
    private SprintRepository sprintRepository;

    @Inject
    public DbSprintGateway(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    @Override public Sprint save(Sprint sprint) {
        SprintModel sprintModel = new SprintModel();
        sprintModel.setStartDate(sprint.getStartDate());
        sprintModel.setEndDate(sprint.getEndDate());
        sprintModel.setTitle(sprint.getTitle());
        SprintModel newSprintModel = sprintRepository.save(sprintModel);

        return new Sprint(newSprintModel.getId(), newSprintModel.getStartDate(), newSprintModel.getEndDate(), sprintModel.getTitle());
    }

    @Override public List<Sprint> find() {
        List<SprintModel> sprintModels = sprintRepository.findAll();
        return sprintModels.stream()
                .map(s -> new Sprint(s.getId(), s.getStartDate(), s.getEndDate(), s.getTitle()))
                .collect(Collectors.toList());
    }

    @Override public List<Sprint> findNonExpired() {
        List<SprintModel> sprintModels = sprintRepository.findAll();
        return sprintModels.stream()
                .map(s -> new Sprint(s.getId(), s.getStartDate(), s.getEndDate(), s.getTitle()))
                .filter(Sprint::notExpired)
                .collect(Collectors.toList());
    }
}
