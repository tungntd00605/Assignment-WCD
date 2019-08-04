package com.assignment.controller;

import com.assignment.dto.UserDTO;
import com.assignment.entity.User;
import com.assignment.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = ofy().load().type(User.class).id(email).now();
        if(StringUtil.comparePasswordWithSalt(password, user.getSalt(), user.getPassword())){
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", new UserDTO(user));
        }
        resp.sendRedirect("/user/detail");
    }
}
