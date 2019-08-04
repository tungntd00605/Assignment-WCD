<%--
  Created by IntelliJ IDEA.
  User: HP ENVY
  Date: 8/3/2019
  Time: 6:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <link rel="stylesheet" href="/assets/bootstrap-4.3.1-dist/css/bootstrap.css">
    <script src="/assets/js/jquery-3.4.1.min.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/bootstrap-4.3.1-dist/js/bootstrap.min.js"></script>
    
    <title>${bundle.homepage}</title>
  </head>
  <body>
  <a href="/change-language?lang=vi">Vi</a>&nbsp;
  <a href="/change-language?lang=en">En</a>
  <div>
    <a href="/user/login" class="btn btn-primary">${bundle.login}</a> &nbsp;
    <a href="/user/register" class="btn btn-secondary">${bundle.register}</a>
  </div>
  </body>
</html>
