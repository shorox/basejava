package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    private StorageException stExc;
    private ConnectionFactory connection;
    private String sqlQuery;

    public SqlHelper(StorageException stExc, ConnectionFactory connection, String sqlQuery) {
        this.stExc = stExc;
        this.connection = connection;
        this.sqlQuery = sqlQuery;
    }

    public Object execute(Helper helper) {
        Object o;
        try (Connection conn = connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            o = helper.executePrepareStatement(ps);
        } catch (SQLException e) {
            throw stExc;
        }
        return o;
    }
}
