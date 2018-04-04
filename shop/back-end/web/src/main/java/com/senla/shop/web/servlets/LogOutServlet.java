package com.senla.shop.web.servlets;

import com.senla.shop.web.token.TokenRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/out")
public class LogOutServlet extends HttpServlet {

    private static final String TOKEN = "token";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter(TOKEN);
        TokenRepository.getInstance().removeToken(token);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}