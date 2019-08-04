package com.assignment.controller;

import com.assignment.dto.UserDTO;
import com.assignment.entity.User;
import com.assignment.util.StringUtil;
import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.ServingUrlOptions;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class UserDetailController extends HttpServlet {
    private BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/user-detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");

        User user = ofy().load().type(User.class).id(email).now();
        if(user == null){
            resp.getWriter().println("User not exit or deleted");
        }
        if(blobstoreService.getUploads(req) != null && !blobstoreService.getUploads(req).isEmpty() ){
            Map<String, List<BlobKey>> blobs = blobstoreService.getUploads(req);
            List<BlobKey> blobKeys = blobs.get("avatar");
            if (blobKeys != null || !blobKeys.isEmpty()) {
                user.setAvatar(blobKeys.get(0).getKeyString());
            }
        }
        user.setFullname(fullname);
        ofy().save().entities(user);
        req.getSession().setAttribute("currentUser", new UserDTO(user));
        resp.getWriter().println("Changed user profile");
    }
}
