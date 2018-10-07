<%--
  Created by IntelliJ IDEA.
  User: liwei
  Date: 2018/9/20
  Time: 10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="j_security_check">
    <input type="text" name="j_username">
    <input type="password" name="j_password" autocomplete="off">
    <input type="submit" value="submit">
</form>
</body>
</html>
