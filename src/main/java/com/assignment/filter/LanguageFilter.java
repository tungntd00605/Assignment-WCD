package com.assignment.filter;

import com.assignment.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageFilter implements Filter {

    private static String currentLanguage = Locale.getDefault().getLanguage();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        filterConfig.getServletContext().setAttribute("bundle", ResourceBundle.getBundle("language"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String language = (String) request.getSession().getAttribute("lang");
        if (language != null && !language.equals(currentLanguage)) {
            System.out.println("Change resource.");
            Locale.setDefault(new Locale(language));
            currentLanguage = language;
            StringUtil.changeLanguage(request);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
