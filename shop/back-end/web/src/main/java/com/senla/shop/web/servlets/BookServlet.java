package com.senla.shop.web.servlets;

import com.senla.shop.model.Book;
import com.senla.shop.web.Controller;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(BookServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        if (req.getParameterMap().containsKey("id")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            resp.getWriter().println(Controller.getInstance().getBook(id));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Book book = null;
        try {
            String title = req.getParameter("title");
            int price = Integer.parseInt(req.getParameter("price"));
            boolean isStore = Boolean.parseBoolean(req.getParameter("isStore"));
            Date datePublished = df.parse(req.getParameter("datePublished"));
            Date dateReceipted = df.parse(req.getParameter("dateReceipted"));
            book = new Book(title, price, isStore, datePublished, dateReceipted);
        } catch (ParseException e) {
            LOGGER.error(e);
        }

        if (book != null) {
            Controller.getInstance().addBook(book);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
