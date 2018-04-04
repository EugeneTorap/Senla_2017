package com.senla.shop.web.filter;

import com.senla.shop.api.facade.IFacade;
import com.senla.shop.view.Facade;
import com.senla.shop.web.token.TokenManager;
import com.senla.shop.web.token.TokenRepository;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private IFacade facade = Facade.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        String token = req.getParameter("token");
        if (token != null && TokenRepository.getInstance().isTokenExist(token)
                && TokenManager.validateToken(token)) {
            filterChain.doFilter(req, resp);
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    public void destroy() {

    }
}
