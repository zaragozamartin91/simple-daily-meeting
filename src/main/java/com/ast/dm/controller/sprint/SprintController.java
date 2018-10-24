package com.ast.dm.controller.sprint;

import com.ast.dm.entity.sprint.InvalidSprintException;
import com.ast.dm.interactor.sprint.CreateSprint;
import com.ast.dm.interactor.sprint.CreateSprintRequest;
import com.ast.dm.interactor.sprint.CreateSprintResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SprintController {
    private final CreateSprint createSprint;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public SprintController(CreateSprint createSprint) {
        this.createSprint = createSprint;
    }

    @RequestMapping(path = "/sprintform", method = RequestMethod.GET)
    public String getSprintForm() {
        return "sprintform";
    }

    @RequestMapping(path = "/sprint", method = RequestMethod.POST)
    public String createSprint(SprintForm sprintForm, Model model) throws ParseException {
        //        CreateSprintRequest createSprintRequest = new CreateSprintRequest(sprintForm.getStartDate(), sprintForm.getEndDate());

        Date startDate;
        Date endDate;
        try {
            startDate = dateFormat.parse(sprintForm.getStartDate());
            endDate = dateFormat.parse(sprintForm.getEndDate());
        } catch (ParseException e) {
            throw new InvalidSprintException("Fechas ingresadas son invalidas!");
        }

        CreateSprintRequest createSprintRequest = new CreateSprintRequest(startDate, endDate);
        CreateSprintResponse createSprintResponse = createSprint.execute(createSprintRequest);
        model.addAttribute("sprintId", createSprintResponse.id);
        return "sprintform";
    }

    @ExceptionHandler(InvalidSprintException.class)
    public String handleInvalidSprint(InvalidSprintException ex, Model model) {
        model.addAttribute("error", ex.getMessage());
        return "sprintform";
    }
}
