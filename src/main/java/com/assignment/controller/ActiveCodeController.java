package com.assignment.controller;

import com.assignment.entity.ActivationCode;
import com.assignment.entity.User;
import com.assignment.util.MailUtil;
import com.assignment.util.StringUtil;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class ActiveCodeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        ActivationCode activationCode = ofy().load().type(ActivationCode.class).id(code).now();
        if(activationCode.getExpiredTime() > Calendar.getInstance().getTimeInMillis() && activationCode.getStatus() == 1){
            resp.getWriter().println("Your account is active now. Please login to check");
        } else {
            resp.getWriter().println("Your activation code has been expired");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        User user = ofy().load().type(User.class).id(email).now();
        if(user != null){
            ActivationCode activationCode = ActivationCode.Builder.anActivationCode()
                    .withCode(StringUtil.generateActivationCode())
                    .withExpiredTime(StringUtil.generateActivationCodeExpiredTime())
                    .withUser(Ref.create(Key.create(User.class, email)))
                    .withStatus(1)
                    .build();

            ofy().save().entities(activationCode);
            MailUtil.sendMail(email, activationCode.getCode());
        }
        resp.getWriter().println("Check your email for activation code");
    }
}
