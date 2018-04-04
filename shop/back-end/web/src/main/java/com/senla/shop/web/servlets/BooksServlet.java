package com.senla.shop.web.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senla.shop.api.facade.IFacade;
import com.senla.shop.enums.SortingType;
import com.senla.shop.model.Book;
import com.senla.shop.view.Facade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BooksServlet extends HttpServlet{

    private IFacade facade = Facade.getInstance();
    private static final String ORDER_BY = "orderby";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        SortingType order = SortingType.valueOf(req.getParameter(ORDER_BY));
        List<Book> books = facade.sortBooksBy(order);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(books);

        resp.getWriter().println(json);
    }
}
