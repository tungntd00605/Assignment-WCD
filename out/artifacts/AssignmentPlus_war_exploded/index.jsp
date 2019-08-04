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
