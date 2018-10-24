package com.ast.dm.controller.sprint;

import com.ast.dm.entity.sprint.InvalidSprintException;
import com.ast.dm.interactor.sprint.CreateSprint;
import com.ast.dm.interactor.sprint.CreateSprintRequest;
import com.ast.dm.interactor.sprint.CreateSprintResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    public String createSprint(SprintForm sprintForm, RedirectAttributes redirectAttributes) throws ParseException {
        //        CreateSprintRequest createSprintRequest = new CreateSprintRequest(sprintForm.getStartDate(), sprintForm.getEndDate());

        Date startDate;
        Date endDate;
        try {
            startDate = dateFormat.parse(sprintForm.getStartDate());
            endDate = dateFormat.parse(sprintForm.getEndDate());
        } catch (ParseException e) {
            throw new InvalidSprintException("Fechas ingresadas son invalidas!");
        }

        CreateSprintRequest createSprintRequest = new CreateSprintRequest(startDate, endDate, sprintForm.getTitle());
        CreateSprintResponse createSprintResponse = createSprint.execute(createSprintRequest);
        redirectAttributes.addFlashAttribute("sprintId", createSprintResponse.id);
        return "redirect:/sprintform";
    }

    @ExceptionHandler(InvalidSprintException.class)
    public String handleInvalidSprint(InvalidSprintException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/sprintform";
    }
}
