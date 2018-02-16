package com.senla.executor.handler;

import com.senla.api.creator.ICreator;
import com.senla.api.model.IBook;
import com.senla.di.DependencyInjection;
import com.senla.executor.ResultHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class BookHandler implements ResultHandler {

    private static ICreator creator = (ICreator) DependencyInjection.getInstance().getObject(ICreator.class);

    @Override
    public List<IBook> handle(ResultSet resultSet) throws SQLException {
        List<IBook> books = new ArrayList<>();

        while (resultSet.next()) {
            int bookId = resultSet.getInt("id");
            String title = resultSet.getString("title");
            int price = resultSet.getInt("price");
            boolean isTheBookInStore = resultSet.getBoolean("isStore");
            Date datePublished = resultSet.getDate("datePublished");
            Date dateReceipted = resultSet.getDate("dateReceipted");
            books.add(creator.createBook(bookId, title, price, isTheBookInStore, datePublished, dateReceipted));
        }
        return books;
    }
}
