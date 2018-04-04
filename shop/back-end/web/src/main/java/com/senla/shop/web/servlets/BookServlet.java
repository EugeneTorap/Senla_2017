package com.senla.shop.web.servlets;

import com.google.gson.GsonBuilder;
import com.senla.shop.api.facade.IFacade;
import com.senla.shop.model.Book;
import com.senla.shop.view.Facade;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/book")
public class BookServlet extends HttpServlet {

    private final static Logger LOGGER = Logger.getLogger(BookServlet.class);
    private IFacade facade = Facade.getInstance();

    private static final String ID = "id";
    private static final String TITLE = "title";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter(ID));
        Book book = facade.getBook(id);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(book);

        resp.getWriter().println(json);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer buffer = new StringBuffer();
        String line;
        while ((line = req.getReader().readLine()) != null)
            buffer.append(line);

        JSONObject jsonObject = new JSONObject(buffer.toString());
        String title = (String) jsonObject.get(TITLE);
        facade.addBook(new Book(title));
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
