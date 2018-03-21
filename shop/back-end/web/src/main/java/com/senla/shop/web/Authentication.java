package com.senla.shop.web;

import com.senla.shop.api.facade.IFacade;
import com.senla.shop.model.Audit;
import com.senla.shop.model.Reader;
import com.senla.shop.view.Facade;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Authentication implements Filter {
    private FilterConfig filterConfig = null;
    private IFacade facade = Facade.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        Object object = req.getSession().getAttribute("reader");
        if (object instanceof Reader) {
            Reader reader = (Reader) object;
            facade.addAudit(new Audit(reader));
            String token = reader.getToken();
            if (facade.isExistedToken(token)){
                filterChain.doFilter(req, resp);
            }
            return;
        }
        resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

    @Override
    public void destroy() {
        filterConfig = null;
    }
}
