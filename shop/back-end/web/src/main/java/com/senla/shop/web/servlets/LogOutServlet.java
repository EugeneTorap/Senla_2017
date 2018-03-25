package com.senla.shop.web.servlets;

import com.senla.shop.api.facade.IFacade;
import com.senla.shop.model.Reader;
import com.senla.shop.view.Facade;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogOutServlet extends HttpServlet {

    private static final String TOKEN = "token";
    private IFacade facade = Facade.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(TOKEN);
        if (login != null){
            Reader reader = facade.getByToken(TOKEN);
            reader.setToken(null);
        }
    }
}