package com.ast.dm.util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionManager {
    private String username;
    private String password;
    private String url;

    public DbConnectionManager(String username, String password, String url) {
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public <T> T runTransaction(DbTransaction<T> dbTransaction) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            // este try auto-cierra la conexion al final

            try {
                // este try realiza las operaciones contra la bbdd
                conn.setAutoCommit(false);
                T results = dbTransaction.run(conn);
                conn.commit();
                return results;
            } catch (SQLException e) {
                rollback(conn);
                throw new DbConnectionManagerException("Ocurrio un error al ejecutar las queries contra " + url, e);
            }
        } catch (SQLException e) {
            throw new DbConnectionManagerException("Error al establecer conexion con " + url, e);
        }
    }

    private void rollback(Connection conn) {
        try {
            conn.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
