package com.senla.executor.handler;

import com.senla.executor.ResultHandler;
import com.senla.model.entity.Reader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReaderHandler implements ResultHandler {
    @Override
    public List<Reader> handle(ResultSet resultSet) throws SQLException {
        List<Reader> readers = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("readerId");
            String name = resultSet.getString("name");
            readers.add(new Reader(id, name));
        }
        return readers;
    }
}
