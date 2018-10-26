package com.ast.dm.controller.dmeeting;

import com.ast.dm.interactor.daily.CreateDailyMeeting;
import com.ast.dm.interactor.daily.CreateDailyMeetingRequest;
import com.ast.dm.interactor.daily.CreateDailyMeetingResponse;
import com.ast.dm.interactor.member.GetMembers;
import com.ast.dm.interactor.member.GetMembersResponse;
import com.ast.dm.interactor.sprint.GetSprints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Controller
public class DailyMeetingController {
    private final GetMembers getMembers;
    private final GetSprints getSprints;
    private final CreateDailyMeeting createDailyMeeting;

    @Autowired
    public DailyMeetingController(
            GetMembers getMembers,
            GetSprints getSprints,
            CreateDailyMeeting createDailyMeeting) {
        this.getMembers = getMembers;
        this.getSprints = getSprints;
        this.createDailyMeeting = createDailyMeeting;
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
    public String createDailyMeeting(@RequestBody String body, RedirectAttributes redirectAttributes) throws IOException {
        //        System.out.println(params);

        String decodedBody = URLDecoder.decode(body, "UTF-8");

        String[] fields = decodedBody.split(Pattern.quote("&"));

        /* OBTENGO EL ID DEL SPRINT */
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
        System.out.println("sprint id:" + sprintId);

        /* OBTENGO LOS CHECKBOXES MARCADOS */
        List<String> checkFields = Arrays
                .stream(fields)
                .filter(s -> s.matches("check\\d+=.*"))
                .collect(Collectors.toList());

        List<String> checkKeys = checkFields.stream().map(s -> s.split("=")[0]).collect(Collectors.toList());
        List<Long> checkIds = checkKeys.stream().map(s -> s.replaceAll("check", "")).map(Long::valueOf).collect(Collectors.toList());
        List<String> checkValues = checkFields.stream().map(s -> s.split("=")[1]).collect(Collectors.toList());

        /* OBTENGO LOS TOPICOS DE CADA MIEMBRO */
        List<String> topicFields = Arrays
                .stream(fields)
                .filter(s -> s.matches("topic\\d+=.*"))
                .collect(Collectors.toList());

        List<CreateDailyMeetingRequest.MemberTopic> memberTopicPairs = topicFields.stream()
                .filter(tf -> {
                    String[] topicFieldSplit = tf.split("=");
                    return topicFieldSplit.length == 2;
                })
                .map(tf -> {
                    String[] topicFieldSplit = tf.split("=");
                    String topicKey = topicFieldSplit[0];
                    Long memberId = Long.valueOf(topicKey.replaceAll("topic", ""));
                    String topicValue = topicFieldSplit[1];
                    return new CreateDailyMeetingRequest.MemberTopic(memberId, topicValue);
                }).collect(Collectors.toList());

        System.out.println(memberTopicPairs);

        if (memberTopicPairs.isEmpty()) {
            throw new InvalidDailyMeetingException("No se asignaron topicos a la daily!");
        }

        CreateDailyMeetingRequest createDailyMeetingRequest =
                new CreateDailyMeetingRequest(new Date(), sprintId, memberTopicPairs);
        CreateDailyMeetingResponse createDailyMeetingResponse = createDailyMeeting.execute(createDailyMeetingRequest);

        redirectAttributes.addFlashAttribute("dmeetingId", createDailyMeetingResponse.id);

        return "redirect:/dmeetingform";
    }

    @ExceptionHandler(InvalidSprintIdException.class)
    public String handleInvalidSprintIdException(InvalidSprintIdException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/dmeetingform";
    }

    @ExceptionHandler(InvalidDailyMeetingException.class)
    public String handleInvalidDailyMeetingException(InvalidDailyMeetingException ex, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error", ex.getMessage());
        return "redirect:/dmeetingform";
    }

}
