package com.mah.repository.impl;

import com.mah.entity.User;
import com.mah.repository.UserRepository;
import com.mah.shared.jdbcutils.PostgresJdbcUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepositoryImpl implements UserRepository {

    private static UserRepository userRepository = null;

    private UserRepositoryImpl() {
    }

    public static UserRepository getUserRepositoryImpl() {
        if (userRepository == null) {
            userRepository = new UserRepositoryImpl();
        }
        return userRepository;
    }

    @Override
    public boolean registerUser(User user) {
        int executeUpdate = 0;
        String valuesForRegisterUserQuery = String.format("'%s', '%s', '%s', '%s', '%s', '%s'", user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getUsername(), user.getPassword());
        String registerUserQuery = "INSERT INTO tbl_users(id, first_name, last_name, email, username, password) VALUES (" + valuesForRegisterUserQuery + ");";
        Connection postgresJdbcConnection;
        Statement statement = null;
        try {
            postgresJdbcConnection = PostgresJdbcUtils.getPostgresJdbcConnection();
            if (postgresJdbcConnection != null) {
                statement = postgresJdbcConnection.createStatement();
            }
            if (statement != null) {
                executeUpdate = statement.executeUpdate(registerUserQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return executeUpdate > 0;
    }

    @Override
    public User loginUser(String emailOrUserName, String password) {
        String loginQuery = "SELECT * FROM tbl_users WHERE password = (SELECT password FROM tbl_users WHERE email = '" + emailOrUserName + "' OR username = '" + emailOrUserName + "') AND password = '" + password + "';";
        User user = null;
        Connection postgresJdbcConnection;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            postgresJdbcConnection = PostgresJdbcUtils.getPostgresJdbcConnection();
            if (postgresJdbcConnection != null) {
                statement = postgresJdbcConnection.createStatement();
            }
            if (statement != null) {
                resultSet = statement.executeQuery(loginQuery);
            }
            if (resultSet != null && resultSet.next()) {
                user = new User(resultSet.getString("id"), resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getString("email"), resultSet.getString("username"), resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

}
