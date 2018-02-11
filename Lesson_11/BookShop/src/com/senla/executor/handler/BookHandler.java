package com.senla.executor.handler;

import com.senla.executor.ResultHandler;
import com.senla.model.entity.Book;
import com.senla.model.entity.Reader;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class BookHandler implements ResultHandler {
    @Override
    public List<Book> handle(ResultSet resultSet) throws SQLException {
        List<Book> books = new ArrayList<>();
        List<Reader> readers = new ArrayList<>();

        int bookId = 0;
        String title = null;
        int price = 0;
        boolean isTheBookInStore = true;
        Date datePublished = null;
        Date dateReceipted = null;

        while (resultSet.next()) {

            if (bookId != resultSet.getInt("bookId")){

                if (bookId != 0){
                    books.add(new Book(bookId, title, price, isTheBookInStore, datePublished, dateReceipted, readers));
                }
                bookId = resultSet.getInt("bookId");
                title = resultSet.getString("title");
                price = resultSet.getInt("price");
                isTheBookInStore = resultSet.getBoolean("isTheBookInStore");
                datePublished = resultSet.getDate("datePublished");
                dateReceipted = resultSet.getDate("dateReceipted");
                readers = new ArrayList<>();
            }

            int readerId = resultSet.getInt("readerId");
            String name = resultSet.getString("name");
            readers.add(new Reader(readerId, name));
        }
        return books;
    }
}
