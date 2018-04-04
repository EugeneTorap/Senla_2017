package com.senla.shop.web.servlets;

import com.senla.shop.api.facade.IFacade;
import com.senla.shop.model.Reader;
import com.senla.shop.view.Facade;
import com.senla.shop.web.token.TokenManager;
import com.senla.shop.web.token.TokenRepository;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private IFacade facade = Facade.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = req.getReader().readLine()) != null)
            buffer.append(line);

        JSONObject jsonObject = new JSONObject(buffer.toString());

        String login = (String) jsonObject.get(LOGIN);
        String password = (String) jsonObject.get(PASSWORD);

        Reader reader = facade.getByLogin(login);
        try {
            if (reader != null) {
                String token = TokenManager.generateToken(login, password);
                TokenRepository.getInstance().putToken(token, reader);

                resp.getOutputStream().print(token);
            } else {
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
