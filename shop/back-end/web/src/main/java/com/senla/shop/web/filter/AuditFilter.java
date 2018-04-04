package com.senla.shop.web.filter;

import com.senla.shop.api.facade.IFacade;
import com.senla.shop.model.Audit;
import com.senla.shop.model.Reader;
import com.senla.shop.view.Facade;
import com.senla.shop.web.token.TokenRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@WebFilter("/*")
public class AuditFilter implements Filter {

    private IFacade facade = Facade.getInstance();
    private static final String TOKEN = "token";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getParameter(TOKEN);
        Reader reader = TokenRepository.getInstance().getUserByToken(token);
        if (reader != null) {
            facade.addAudit(new Audit(reader));
            filterChain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
