package com.ast.dm.controller.dmeeting;

import com.ast.dm.interactor.member.GetMembers;
import com.ast.dm.interactor.member.GetMembersResponse;
import com.ast.dm.interactor.sprint.GetSprints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
public class DailyMeetingController {
    private final GetMembers getMembers;
    private final GetSprints getSprints;

    @Autowired
    public DailyMeetingController(GetMembers getMembers, GetSprints getSprints) {
        this.getMembers = getMembers;
        this.getSprints = getSprints;
    }

    @ModelAttribute(name = "members")
    public List<MemberData> members() {
        GetMembersResponse getMembersResponse = getMembers.execute();
        return getMembersResponse.memberItems.stream()
                .map(m -> new MemberData(m.id, m.username, m.name, m.role))
                .collect(Collectors.toList());
    }

    @ModelAttribute(name = "sprints")
    public List<SprintData> sprints() {
        return getSprints.findNonExpired().getSprintItems().stream()
                .map(s -> new SprintData(s.id, s.startDate, s.endDate, s.title))
                .collect(Collectors.toList());
    }

    @GetMapping("/dmeetingform")
    public String getDailyMeetingForm() {
        return "dmeetingform";
    }

    @PostMapping(value = "/dmeeting", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    //    @PostMapping(value = "/dmeeting")
    public String createDailyMeeting(@RequestBody String body, Model model) throws IOException {
        //        System.out.println(params);

        String decodedBody = URLDecoder.decode(body, "UTF-8");

        String[] fields = decodedBody.split(Pattern.quote("&"));

        String sprintField = Arrays
                .stream(fields)
                .filter(s -> s.matches("sprint=.*"))
                .findFirst()
                .orElse("sprint=X");

        long sprintId;
        try {
            sprintId = Long.valueOf(sprintField.split("=")[1]);
        } catch (Exception e) {
            throw new InvalidSprintIdException("Id de sprint invalido!", e);
        }

        List<String> checkFields = Arrays
                .stream(fields)
                .filter(s -> s.matches("check\\d+=.*"))
                .collect(Collectors.toList());



        List<String> topicFields = Arrays
                .stream(fields)
                .filter(s -> s.matches("topic\\d+=.*"))
                .collect(Collectors.toList());

        return "redirect:/dmeetingform";
    }

    @ExceptionHandler(InvalidSprintIdException.class)
    public String handleInvalidSprintIdException(InvalidSprintIdException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/dmeetingform";
    }
}
