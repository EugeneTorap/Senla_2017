package com.senla.shop.web.servlets;

import com.senla.shop.api.facade.IFacade;
import com.senla.shop.model.Reader;
import com.senla.shop.util.EncryptUtils;
import com.senla.shop.view.Facade;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogInServlet extends HttpServlet {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private IFacade facade = Facade.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        Reader reader = facade.getByLogin(login);
        if (reader != null){
            reader.setToken(EncryptUtils.encodeToken(login + password));
        }
    }
}
