package ru.javawebinar.basejava.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlHelper {

    public PreparedStatement getPrepareStatement(ConnectionFactory connection, String sqlQuery) throws SQLException {
        return connection.getConnection().prepareStatement(sqlQuery);
    }
}
