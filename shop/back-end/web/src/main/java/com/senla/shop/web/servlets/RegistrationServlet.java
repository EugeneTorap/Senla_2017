package com.senla.shop.web.servlets;

import com.senla.shop.api.facade.IFacade;
import com.senla.shop.model.Reader;
import com.senla.shop.view.Facade;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/join")
public class RegistrationServlet extends HttpServlet {

    private IFacade facade = Facade.getInstance();
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = req.getReader().readLine()) != null)
            buffer.append(line);

        JSONObject jsonObject = new JSONObject(buffer.toString());
        String login = (String) jsonObject.get(LOGIN);
        String password = (String) jsonObject.get(PASSWORD);
        if (login != null && password != null){
            facade.addReader(new Reader(login, password));
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
