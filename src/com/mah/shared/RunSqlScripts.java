package com.mah.shared;

import com.mah.shared.jdbcutils.PostgresJdbcUtils;
import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RunSqlScripts {

    private static List<String> getAllTablesAlreadyCreated() {
        Connection postgresJdbcConnection = null;
        DatabaseMetaData metaData = null;
        ResultSet resultSetOfTables = null;
        List<String> listOfTableNames = new ArrayList<>();
        postgresJdbcConnection = PostgresJdbcUtils.getPostgresJdbcConnection();
        try {
            String table[] = {"TABLE"};
            if (postgresJdbcConnection != null) {
                metaData = postgresJdbcConnection.getMetaData();
            }
            if (metaData != null) {
                resultSetOfTables = metaData.getTables(null, null, null, table);
            }
            if (resultSetOfTables != null) {
                while (resultSetOfTables.next()) {
                    listOfTableNames.add(resultSetOfTables.getString("TABLE_NAME"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PostgresJdbcUtils.closePostgresJdbcConnection();
        return listOfTableNames;
    }

    private static void createTable(Connection connection, String fileNameOfTableToBeCreated) {
        try {
            new ScriptRunner(connection).runScript(new BufferedReader(new FileReader(String.format("/home/admin/my-git-workspace/java-basic-todo-console-app/src/com/mah/resources/%s.sql", fileNameOfTableToBeCreated))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<String> getAllTablesToBeCreated() {
        List<String> listOfAllTablesToBeCreated = new ArrayList<>();
        listOfAllTablesToBeCreated.add("tbl_users");
        listOfAllTablesToBeCreated.add("tbl_tasks");
        return listOfAllTablesToBeCreated;
    }

    private static void createTablesIfNotExists() {
        List<String> listOfTablesAlreadyCreated = getAllTablesAlreadyCreated();
        List<String> listOfAllTablesToBeCreated = getAllTablesToBeCreated();
        List<String> listOfTablesToBeCreated = new ArrayList<>();

        for (String tableToBeCreated : listOfAllTablesToBeCreated) {
            if (!listOfTablesAlreadyCreated.contains(tableToBeCreated)) {
                listOfTablesToBeCreated.add(tableToBeCreated);
            }
        }

        Connection postgresJdbcConnection = null;
        postgresJdbcConnection = PostgresJdbcUtils.getPostgresJdbcConnection();
        if (!listOfTablesToBeCreated.isEmpty() && postgresJdbcConnection != null) {
            for (String tableToBeCreated : listOfTablesToBeCreated) {
                createTable(postgresJdbcConnection, tableToBeCreated);
            }
        }
    }

    public static void main(String[] args) {
        createTablesIfNotExists();
    }
}
