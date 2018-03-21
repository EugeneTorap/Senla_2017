package com.senla.shop.web.servlets;

import com.senla.shop.enums.Status;
import com.senla.shop.model.Book;
import com.senla.shop.model.Order;
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

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(OrderServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        if (req.getParameterMap().containsKey("id")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            resp.getWriter().println(Controller.getInstance().getOrder(id));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Order order = null;
        try {
            int price = Integer.parseInt(req.getParameter("price"));
            Date dateExecuted = df.parse(req.getParameter("dateExecuted"));
            Status status = Status.valueOf(req.getParameter("status"));
            order = new Order(dateExecuted, status, price);
        } catch (ParseException e) {
            LOGGER.error(e);
        }

        if (order != null) {
            Controller.getInstance().addOrder(order);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
