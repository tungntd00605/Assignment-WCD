<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobKey" %>
<%@ page import="com.google.appengine.api.images.ImagesService" %>
<%@ page import="com.google.appengine.api.images.ImagesServiceFactory" %>
<%@ page import="com.assignment.dto.UserDTO" %>
<%@ page import="com.google.appengine.api.images.ServingUrlOptions" %><%--
  Created by IntelliJ IDEA.
  User: HP ENVY
  Date: 8/3/2019
  Time: 9:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
    ImagesService imagesService = ImagesServiceFactory.getImagesService();
    UserDTO currentUser = (UserDTO) request.getSession().getAttribute("currentUser");
%>
<html>
<head>
    <link rel="stylesheet" href="/assets/bootstrap-4.3.1-dist/css/bootstrap.css">
    <script src="/assets/js/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>

    <title>User Page</title>
</head>
<body>
    <%--<c:set var="currentUser" scope="session" value="${sessionScope.get('currentUser')}"/>--%>
    <a href="/change-language?lang=vi">Vi</a>&nbsp;
    <a href="/change-language?lang=en">En</a>
    <h1>Welcome <c:out value="${currentUser.fullname}"/></h1>
    <c:if test="${currentUser.status == 0}">
        <p>Your account is inactive. Check your email to active</p>
        <form action="/user/active" method="post">
            <input type="hidden" name="email" value="${currentUser.email}">
            <button class="btn btn-primary" type="submit" >Resend</button>
        </form>
    </c:if>
    <c:if test="${currentUser.status == 1}">
        <p>Your account is active. Click button to re-send activation code</p>
    </c:if>


    <form action="<%= blobstoreService.createUploadUrl("/user/detail") %>" method="post" enctype="multipart/form-data">
        <div class="form-group col-6">
            <label>${bundle.email}</label>
            <input type="text" name="email" class="form-control disabled" value="${currentUser.email}" readonly>
        </div>

        <div class="form-group col-6">
            <label>${bundle.fullname}</label>
            <input type="text" name="fullname" class="form-control" value="${currentUser.fullname}">
        </div>

        <div class="form-group col-6">
            <label>${bundle.avatar}</label>
            <div>
                <img src="<%= imagesService.getServingUrl(
                    ServingUrlOptions.Builder
                    .withBlobKey(new BlobKey(currentUser.getAvatar()))
                    .imageSize(150)
                    .crop(true)
                    .secureUrl(true)
            )%>" alt="">
            </div>
            <input type="file" name="avatar" class="form-control-file">
        </div>

        <div class="form-group col-6">
            <input type="submit" class="btn btn-primary" value="${bundle.save}"/>
            <input type="reset" class="btn btn-secondary" value="${bundle.reset}"/>
        </div>
    </form>
</body>
</html>
