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
    public final SqlHelper sqlHelper = new SqlHelper();

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void save(Resume resume) {
        try (PreparedStatement ps = sqlHelper.getPrepareStatement(connectionFactory, "INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
            ps.setString(1, resume.getUuid());
            ps.setString(2, resume.getFullName());
            ps.execute();
        } catch (SQLException e) {
            throw new ExistStorageException(resume.getUuid());
        }
    }

    @Override
    public Resume get(String uuid) {
        try (PreparedStatement ps = sqlHelper.getPrepareStatement(connectionFactory, "SELECT * FROM resume r WHERE r.uuid =?")) {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void update(Resume resume) {
        try (PreparedStatement ps = sqlHelper.getPrepareStatement(connectionFactory, "UPDATE resume SET full_name = ? WHERE uuid = ?")) {
            ps.setString(1, resume.getFullName());
            ps.setString(2, resume.getUuid());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void delete(String uuid) {
        try (PreparedStatement ps = sqlHelper.getPrepareStatement(connectionFactory, "DELETE FROM resume WHERE uuid = ?")) {
            ps.setString(1, uuid);
            int delete = ps.executeUpdate();
            if (delete == 0) {
                throw new NotExistStorageException(uuid);
            }
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public int size() {
        try (PreparedStatement ps = sqlHelper.getPrepareStatement(connectionFactory, "SELECT * FROM resume")) {
            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            return count;
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public void clear() {
        try (PreparedStatement ps = sqlHelper.getPrepareStatement(connectionFactory, "DELETE FROM resume")) {
            ps.execute();
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = new ArrayList<>();
        try (PreparedStatement ps = sqlHelper.getPrepareStatement(connectionFactory, "SELECT * FROM resume")) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name")));
            }
            return list.stream().sorted().collect(Collectors.toList());
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
