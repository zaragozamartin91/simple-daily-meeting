package com.ast.dm.util.db;

public class DbConnectionManagerException extends RuntimeException {
    public DbConnectionManagerException(String message) {
        super(message);
    }

    public DbConnectionManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
