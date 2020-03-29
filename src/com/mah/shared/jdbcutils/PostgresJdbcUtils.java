package com.mah.shared.jdbcutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresJdbcUtils {
    private static final String JDBC_POSTGRES_DRIVER_CLASS_NAME = "org.postgresql.Driver";
    private static final String JDBC_POSTGRES_URL = "jdbc:postgresql://localhost:5432/java-basic-todo-console-app";
    private static final String JDBC_POSTGRES_USERNAME = "postgres";
    private static final String JDBC_POSTGRES_PASSWORD = "root";
    private static Connection connection = null;

    private PostgresJdbcUtils() {
    }

    public static Connection getPostgresJdbcConnection() {
        try {
            Class.forName(JDBC_POSTGRES_DRIVER_CLASS_NAME);
            connection = DriverManager.getConnection(JDBC_POSTGRES_URL, JDBC_POSTGRES_USERNAME, JDBC_POSTGRES_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closePostgresJdbcConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
