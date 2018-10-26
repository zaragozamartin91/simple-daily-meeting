package com.ast.dm;

import com.ast.dm.controller.dmeeting.InvalidSprintIdException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ParseWwwFormUrlEncodedTest {
    @Test
    public void parse() {
        String body = "_csrf=270c0bd0-327d-4822-a793-a3c0f2585f92&sprint=5&topic1=mutual+auth&check1=on&topic2=no+se+que+onda&check2=on&topic3=cosas+de+jefe+super+administrador&check3=on";

        String decodedBody = "_csrf=270c0bd0-327d-4822-a793-a3c0f2585f92"
                + "&sprint=5"
                + "&topic1=mutual auth"
                + "&check1=on"
                + "&topic2=no se que onda"
                + "&check2=on"
                + "&topic3=cosas de jefe super administrador"
                + "&check3=on";

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
        System.out.println("sprint id:" + sprintId);

        List<String> checkFields = Arrays
                .stream(fields)
                .filter(s -> s.matches("check\\d+=.*"))
                .collect(Collectors.toList());

        List<String> checkKeys = checkFields.stream().map(s -> s.split("=")[0]).collect(Collectors.toList());
        List<Long> checkIds = checkKeys.stream().map(s -> s.replaceAll("check", "")).map(Long::valueOf).collect(Collectors.toList());
        List<String> checkValues = checkFields.stream().map(s -> s.split("=")[1]).collect(Collectors.toList());

        List<String> topicFields = Arrays
                .stream(fields)
                .filter(s -> s.matches("topic\\d+=.*"))
                .collect(Collectors.toList());

        List<MemberTopicPair> memberTopicPairs = topicFields.stream().map(tf -> {
            String topicKey = tf.split("=")[0];
            Long memberId = Long.valueOf(topicKey.replaceAll("topic", ""));
            String topicValue = tf.split("=")[1];
            return new MemberTopicPair(memberId, topicValue);
        }).collect(Collectors.toList());

        System.out.println(memberTopicPairs);
    }

    static class MemberTopicPair {
        public final long memberId;
        public final String topicValue;

        MemberTopicPair(long memberId, String topicValue) {
            this.memberId = memberId;
            this.topicValue = topicValue;
        }

        @Override public String toString() {
            final StringBuilder sb = new StringBuilder("MemberTopicPair{");
            sb.append("memberId=").append(memberId);
            sb.append(", topicValue='").append(topicValue).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }
}
