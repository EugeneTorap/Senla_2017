package com.senla.shop.web.servlets;

import com.senla.shop.model.Reader;
import com.senla.shop.web.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reader")
public class ReaderServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        if (req.getParameterMap().containsKey("id")) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            resp.getWriter().println(Controller.getInstance().getReader(id));
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String name = req.getParameter("name");
        Reader reader = new Reader(name);

        if (reader != null) {
            Controller.getInstance().addReader(reader);
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
