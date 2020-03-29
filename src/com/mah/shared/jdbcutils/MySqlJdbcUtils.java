package com.mah.shared.jdbcutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlJdbcUtils {

    private static final String JDBC_MYSQL_DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String JDBC_MYSQL_URL = "jdbc:mysql://localhost:3306/java-basic-todo-console-app";
    private static final String JDBC_MYSQL_USERNAME = "root";
    private static final String JDBC_MYSQL_PASSWORD = "root";

    private static Connection connection = null;

    private MySqlJdbcUtils() {
    }

    public static Connection getMySqlJdbcConnection() {
        try {
            Class.forName(JDBC_MYSQL_DRIVER_CLASS_NAME);
            connection = DriverManager.getConnection(JDBC_MYSQL_URL, JDBC_MYSQL_USERNAME, JDBC_MYSQL_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeMySqlJdbcConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}