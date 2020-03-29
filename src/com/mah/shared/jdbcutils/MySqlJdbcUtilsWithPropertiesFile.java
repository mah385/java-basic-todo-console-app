package com.mah.shared.jdbcutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySqlJdbcUtilsWithPropertiesFile {

    private static Connection connection = null;

    private MySqlJdbcUtilsWithPropertiesFile() {
    }

    public static Connection getMySqlJdbcConnection() {
        try(InputStream inputStream = new FileInputStream("com/mah/resources/application.properties")) {
            Properties prop = new Properties();
            prop.load(inputStream);

            Class.forName(prop.getProperty("jdbc.mysql.driver-class-name"));
            connection = DriverManager.getConnection(prop.getProperty("jdbc.mysql.url"), prop.getProperty("jdbc.mysql.username"), prop.getProperty("jdbc.mysql.password"));
        } catch (IOException | ClassNotFoundException | SQLException e) {
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