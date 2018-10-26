package com.ast.dm.controller.dmeeting;

public class InvalidDailyMeetingException extends RuntimeException {
    public InvalidDailyMeetingException(String message) {
        super(message);
    }

    public InvalidDailyMeetingException(String message, Throwable cause) {
        super(message, cause);
    }
}
