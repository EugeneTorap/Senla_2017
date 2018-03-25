package com.senla.shop.web.servlets;

import com.senla.shop.enums.SortingType;
import com.senla.shop.web.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/books")
public class BooksServlet extends HttpServlet{

    private static final String ORDER_BY = "orderby";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey(ORDER_BY)) {
            SortingType order = SortingType.valueOf(req.getParameter(ORDER_BY));
            resp.getWriter().println(Controller.getInstance().getBooksSortedBy(order));
        }
    }
}
