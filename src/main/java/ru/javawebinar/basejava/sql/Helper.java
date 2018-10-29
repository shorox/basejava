package ru.javawebinar.basejava.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface Helper<T> {

    T executePrepareStatement(PreparedStatement preparedStatement) throws SQLException;
}
