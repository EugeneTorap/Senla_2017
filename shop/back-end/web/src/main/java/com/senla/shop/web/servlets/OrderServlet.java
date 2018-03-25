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
    private static final String ID = "id";
    private static final String PRICE = "price";
    private static final String DATE = "dateExecuted";
    private static final String STATUS = "status";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameterMap().containsKey(ID)) {
            Integer id = Integer.parseInt(req.getParameter(ID));
            resp.getWriter().println(Controller.getInstance().getOrder(id));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Order order = null;
        try {
            int price = Integer.parseInt(req.getParameter(PRICE));
            Date dateExecuted = df.parse(req.getParameter(DATE));
            Status status = Status.valueOf(req.getParameter(STATUS));
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
