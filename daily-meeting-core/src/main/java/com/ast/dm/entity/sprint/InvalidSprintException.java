package com.ast.dm.entity.sprint;

public class InvalidSprintException extends RuntimeException {
    public InvalidSprintException(String message) {
        super(message);
    }

    public InvalidSprintException(String message, Throwable cause) {
        super(message, cause);
    }
}
