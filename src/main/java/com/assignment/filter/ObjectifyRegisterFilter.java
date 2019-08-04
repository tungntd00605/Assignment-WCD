package com.assignment.filter;

import com.assignment.entity.ActivationCode;
import com.assignment.entity.User;
import com.googlecode.objectify.ObjectifyService;
import javax.servlet.*;
import java.io.IOException;

public class ObjectifyRegisterFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ObjectifyService.register(User.class);
        ObjectifyService.register(ActivationCode.class);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
