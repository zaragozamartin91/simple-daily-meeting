package com.ast.dm.util.db;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface DbTransaction<T> {
    T run(Connection connection) throws SQLException;
}
