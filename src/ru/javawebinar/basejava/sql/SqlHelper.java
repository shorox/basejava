package ru.javawebinar.basejava.sql;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    private final ConnectionFactory connectionFactory;

    public SqlHelper(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public Object execute(String uuid, String sqlQuery, Helper helper) {
        Object o;
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlQuery)) {
            o = helper.executePrepareStatement(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals("null")) {
                throw new StorageException(e);
            }
            throw new ExistStorageException(uuid);
        }
        return o;
    }
}
