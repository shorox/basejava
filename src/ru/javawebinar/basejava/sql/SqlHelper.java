package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

import java.sql.*;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Object execute(String uuid, String sqlQuery, Helper helper) {
        Object o;
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            o = helper.executePrepareStatement(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals(uuid)) {
                throw new StorageException(e);
            }
            throw new ExistStorageException(uuid);
        }
        return o;
    }
}
