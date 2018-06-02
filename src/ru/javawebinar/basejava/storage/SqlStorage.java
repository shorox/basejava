package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactsType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.sql.DriverManager.*;

public class SqlStorage implements Storage {

    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    doInsert(resume, conn);
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM resume r " +
                            "LEFT JOIN contact c      " +
                            " ON r.uuid = c.resume_uuid     " +
                            "WHERE r.uuid = ?")) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                Resume resume = new Resume(uuid, rs.getString("full_name"));
                do {
                    String value = rs.getString("value");
                    if (value != null) {
                        ContactsType type = ContactsType.valueOf(rs.getString("type"));
                        resume.addContact(type, value);
                    }
                } while (rs.next());
                return resume;
            }
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                int delete = ps.executeUpdate();
                if (delete == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            doDelete(resume.getUuid(), "DELETE FROM contact WHERE resume_uuid = ?", conn);
            doInsert(resume, conn);
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.transactionalExecute(conn -> {
            doDelete(uuid, "DELETE FROM resume WHERE uuid = ?", conn);
            return null;
        });
    }

    @Override
    public int size() {
        return sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("SELECT COUNT (*) FROM resume")) {
                ResultSet rs = ps.executeQuery();
                rs.next();
                return rs.getInt(1);
            }
        });
    }

    @Override
    public void clear() {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM resume")) {
                ps.execute();
                return null;
            }
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        Map<String,Resume> map = new HashMap<>();
        return sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM resume ORDER BY full_name,uuid")) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                   String uuid = rs.getString("uuid");
                    map.put(uuid,new Resume(uuid, rs.getString("full_name")));
                }
            }
            try (PreparedStatement psContact = conn.prepareStatement("SELECT * FROM contact")) {
                ResultSet rsContact = psContact.executeQuery();
                while (rsContact.next()) {
                    for (Map.Entry<String,Resume> e : map.entrySet())
                        if (e.getKey().equals(rsContact.getString("resume_uuid"))) {
                            e.getValue().addContact(ContactsType.valueOf(rsContact.getString("type")),
                                    rsContact.getString("value"));
                        }
                    }
                }
                return  map.values().stream().sorted().collect(Collectors.toList());
        });
}

    private void doInsert(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactsType, String> e : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void doDelete(String uuid, String query, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, uuid);
            int delete = ps.executeUpdate();
            if (delete == 0) {
                throw new NotExistStorageException(uuid);
            }
        }
    }
}
