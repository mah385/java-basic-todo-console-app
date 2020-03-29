package com.mah.shared.jdbcutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresJdbcUtilsWithPropertiesFile {

    private static Connection connection = null;

    private PostgresJdbcUtilsWithPropertiesFile() {
    }

    public static Connection getPostgresJdbcConnection() {
        try(InputStream inputStream = new FileInputStream("com/mah/resources/application.properties")) {
            Properties prop = new Properties();
            prop.load(inputStream);

            Class.forName(prop.getProperty("jdbc.postgres.driver-class-name"));
            connection = DriverManager.getConnection(prop.getProperty("jdbc.postgres.url"), prop.getProperty("jdbc.postgres.username"), prop.getProperty("jdbc.postgres.password"));
        } catch (IOException| ClassNotFoundException | SQLException e) {
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
