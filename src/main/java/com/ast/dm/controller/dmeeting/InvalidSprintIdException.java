package com.ast.dm.controller.dmeeting;

public class InvalidSprintIdException extends RuntimeException {
    public InvalidSprintIdException(String message) {
        super(message);
    }

    public InvalidSprintIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
