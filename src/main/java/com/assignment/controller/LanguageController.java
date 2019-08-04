package com.assignment.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("lang", req.getParameter("lang"));
        String redirectUrl = req.getHeader("Referer");
        if (redirectUrl == null || redirectUrl.isEmpty()) {
            resp.sendRedirect("/");
        } else {
            resp.sendRedirect(redirectUrl);
        }
    }
}
