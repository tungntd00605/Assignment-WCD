package com.assignment.controller;

import com.assignment.entity.ActivationCode;
import com.assignment.entity.User;
import com.assignment.util.MailUtil;
import com.assignment.util.StringUtil;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.Ref;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class RegisterController extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String avatar = "";
        if(blobstoreService.getUploads(req) != null && !blobstoreService.getUploads(req).isEmpty() ){
            Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
            List<BlobKey> blobKeys = blobs.get("avatar");
            if (blobKeys != null || !blobKeys.isEmpty()) {
                avatar = blobKeys.get(0).getKeyString();
            }
        }
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String salt = StringUtil.generateSalt();
        String hashPassword = StringUtil.hashPassword(password, salt);


        User user = User.Builder.anUser()
                .withEmail(email)
                .withPassword(hashPassword)
                .withSalt(salt)
                .withFullname(fullname)
                .withAvatar(avatar)
                .withStatus(0)
                .build();
        if(ofy().save().entities(user).now().isEmpty()){
            // add error message later
            req.getRequestDispatcher("/view/register.jsp").forward(req, resp);
        };

        String codeStr;
        boolean isDuplicateCode = true;
        do {
            codeStr = StringUtil.generateActivationCode();
            ActivationCode oldCode = ofy().load().type(ActivationCode.class).id(codeStr).now();
            if (oldCode == null) {
                isDuplicateCode = false;
            }
        } while (isDuplicateCode);


        ActivationCode activationCode = ActivationCode.Builder.anActivationCode()
                .withCode(codeStr)
                .withExpiredTime(StringUtil.generateActivationCodeExpiredTime())
                .withUser(Ref.create(Key.create(User.class, email)))
                .withStatus(1)
                .build();

        ofy().save().entities(activationCode);

        MailUtil.sendMail(user.getEmail(), activationCode.getCode());


        req.getRequestDispatcher("/view/register.jsp").forward(req, resp);

    }
}
