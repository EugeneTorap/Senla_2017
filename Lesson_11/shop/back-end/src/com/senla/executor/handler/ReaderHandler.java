package com.senla.executor.handler;

import com.senla.api.creator.ICreator;
import com.senla.api.model.IReader;
import com.senla.di.DependencyInjection;
import com.senla.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReaderHandler implements ResultHandler {

    private static ICreator creator = (ICreator) DependencyInjection.getInstance().getObject(ICreator.class);

    @Override
    public List<IReader> handle(ResultSet resultSet) throws SQLException {
        List<IReader> readers = new ArrayList<>();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            readers.add(creator.createReader(id, name));
        }
        return readers;
    }
}
