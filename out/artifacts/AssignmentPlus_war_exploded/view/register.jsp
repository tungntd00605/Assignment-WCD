<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>

<%
    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>
<html>
<head>
    <link rel="stylesheet" href="/assets/bootstrap-4.3.1-dist/css/bootstrap.css">
    <script src="/assets/js/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>

    <title>Register</title
</head>
<body>
    <h1>${bundle.register} ${bundle.page}</h1>
    <a href="/change-language?lang=vi">Vi</a>&nbsp;
    <a href="/change-language?lang=en">En</a>
    <form action="<%= blobstoreService.createUploadUrl("/user/register") %>" method="post" enctype="multipart/form-data">
        <div class="form-group col-6">
            <label>${bundle.email}</label>
            <input type="text" name="email" class="form-control">
        </div>

        <div class="form-group col-6">
            <label>${bundle.password}</label>
            <input type="password" name="password" class="form-control">
        </div>

        <div class="form-group col-6">
            <label>${bundle.fullname}</label>
            <input type="text" name="fullname" class="form-control">
        </div>

        <div class="form-group col-6">
            <label>${bundle.avatar}</label>
            <input type="file" name="avatar" class="form-control-file">
        </div>

        <div class="form-group col-6">
            <input type="submit" class="btn btn-primary" value="${bundle.save}"/>
            <input type="reset" class="btn btn-secondary" value="${bundle.reset}"/>
        </div>
    </form>
</body>
</html>
