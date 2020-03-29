package com.mah.shared;

import com.mah.shared.jdbcutils.PostgresJdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.mah.shared.AnsiColorUtils.ANSI_RESET;
import static com.mah.shared.AnsiColorUtils.ANSI_YELLOW;
import static com.mah.shared.ConsoleColors.CYAN_BOLD_BRIGHT;

public class JDBCSample {
    public static void main(String[] args) {
        Connection postgresJdbcConnection = PostgresJdbcUtils.getPostgresJdbcConnection();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = postgresJdbcConnection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tbl_users");

            System.out.printf(CYAN_BOLD_BRIGHT + "%n %20s %20s %20s %20s %20s %20s %n", "ID", "FIRST_NAME", "LAST_NAME", "EMAIL", "USERNAME", "PASSWORD" + ANSI_RESET);
            while (resultSet.next()) {
                System.out.printf(ANSI_YELLOW + "%n %20s %20s %20s %20s %20s %20s %n", resultSet.getString("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("username"), resultSet.getString("password") + ANSI_RESET);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
