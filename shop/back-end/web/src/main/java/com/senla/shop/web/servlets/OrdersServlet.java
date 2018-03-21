package com.senla.shop.web.servlets;

import com.senla.shop.enums.SortingType;
import com.senla.shop.web.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/orders")
public class OrdersServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        if (req.getParameterMap().containsKey("orderby")) {
            SortingType order = SortingType.valueOf(req.getParameter("orderby"));
            resp.getWriter().println(Controller.getInstance().getOrdersSortedBy(order));
        }
    }
}
