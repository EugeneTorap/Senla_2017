package com.senla.executor.handler;

import com.senla.executor.ResultHandler;
import com.senla.model.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class BookHandler implements ResultHandler {
    @Override
    public List<Book> handle(ResultSet resultSet) throws SQLException {
        List<Book> books = new ArrayList<>();

        while (resultSet.next()) {
            int bookId = resultSet.getInt("bookId");
            String title = resultSet.getString("title");
            int price = resultSet.getInt("price");
            boolean isTheBookInStore = resultSet.getBoolean("isTheBookInStore");
            Date datePublished = resultSet.getDate("datePublished");
            Date dateReceipted = resultSet.getDate("dateReceipted");
            books.add(new Book(bookId, title, price, isTheBookInStore, datePublished, dateReceipted));
        }
        return books;
    }
}
