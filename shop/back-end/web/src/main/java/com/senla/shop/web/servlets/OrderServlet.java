package com.senla.shop.web.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.senla.shop.api.facade.IFacade;
import com.senla.shop.enums.Status;
import com.senla.shop.model.Order;
import com.senla.shop.view.Facade;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(OrderServlet.class);
    private IFacade facade = Facade.getInstance();

    private static final String ID = "id";
    private static final String PRICE = "price";
    private static final String DATE = "dateExecuted";
    private static final String STATUS = "status";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter(ID));
        Order order = facade.getOrder(id);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(order);

        resp.getWriter().println(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = req.getReader().readLine()) != null)
            buffer.append(line);

        JSONObject jsonObject = new JSONObject(buffer.toString());

        Integer id = (Integer) jsonObject.get(ID);
        Integer price = (Integer) jsonObject.get(PRICE);
        Date dateExecuted = (Date) jsonObject.get(DATE);
        Status status = (Status) jsonObject.get(STATUS);
        Order order = new Order(dateExecuted, status, price);
        order.setId(id);
        facade.addOrder(order);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
