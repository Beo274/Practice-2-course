package ru.is;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/QR_1";
    private static final String USER = "postgres";
    private static final String PASSWORD = "qwerty1234";

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL JDBC Driver not found", e);
        }
    }

    public static Connection getConnection() throws Exception, SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
