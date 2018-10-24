package com.ast.dm.controller.dmeeting;

import com.ast.dm.interactor.member.GetMembers;
import com.ast.dm.interactor.member.GetMembersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DailyMeetingController {
    private final GetMembers getMembers;

    @Autowired
    public DailyMeetingController(GetMembers getMembers) {
        this.getMembers = getMembers;
    }

    @ModelAttribute(name = "members")
    public List<MemberData> members() {
        GetMembersResponse getMembersResponse = getMembers.execute();
        return getMembersResponse.memberItems.stream()
                .map(m -> new MemberData(m.id, m.username, m.name, m.role))
                .collect(Collectors.toList());
    }

    @GetMapping("/dmeetingform")
    public String getDailyMeetingForm() {
        return "dmeetingform";
    }

    @PostMapping(value = "/dmeeting", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    //    @PostMapping(value = "/dmeeting")
    public String createDailyMeeting(@RequestBody String body) throws IOException {
        //        System.out.println(params);

        String decodedBody = URLDecoder.decode(body, "UTF-8");

        //        ServletInputStream inputStream = request.getInputStream();
        //        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        //        String line;
        //        while ((line = bufferedReader.readLine()) != null) {
        //            System.out.println(line);
        //        }
        return "redirect:/dmeetingform";
    }
}
