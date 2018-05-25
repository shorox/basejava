package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.ConnectionFactory;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SqlStorage implements Storage {

    public final ConnectionFactory connectionFactory;
    public SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void save(Resume resume) {
        sqlHelper = new SqlHelper(new ExistStorageException(resume.getUuid()), connectionFactory, "INSERT INTO resume (uuid, full_name) VALUES (?,?)");
        sqlHelper.execute(ps -> {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        sqlHelper = new SqlHelper(new StorageException(uuid), connectionFactory, "SELECT * FROM resume r WHERE r.uuid =?");
       return (Resume) sqlHelper.execute(ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper = new SqlHelper(new StorageException("update Exception"), connectionFactory, "UPDATE resume SET full_name = ? WHERE uuid = ?");
        sqlHelper.execute(ps -> {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            ps.executeUpdate();
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper = new SqlHelper(new StorageException("delete Exception"), connectionFactory, "DELETE FROM resume WHERE uuid = ?");
        sqlHelper.execute(ps -> {
            ps.setString(1, uuid);
            int delete = ps.executeUpdate();
            if (delete == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public int size() {
        sqlHelper = new SqlHelper(new StorageException("get size Exception"), connectionFactory, "SELECT * FROM resume");
        return (int) sqlHelper.execute(ps -> {
            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            return count;
        });
    }

    @Override
    public void clear() {
        sqlHelper = new SqlHelper(new StorageException("clear Exception"), connectionFactory, "DELETE FROM resume");
        sqlHelper.execute(ps -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>();
        sqlHelper = new SqlHelper(new StorageException("getAllSorted Exception"), connectionFactory, "SELECT * FROM resume");
        return (List<Resume>) sqlHelper.execute(ps -> {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               list.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name")));
           }
            return list.stream().sorted().collect(Collectors.toList());
        });
    }
}
