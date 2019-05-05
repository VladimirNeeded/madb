<%--
  Created by IntelliJ IDEA.
  User: vohat
  Date: 05.05.2019
  Time: 21:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>

<form action="editPage" method="post">
    <input type="hidden" name="value" value="${value}">
    <input type="hidden" name="login" value="${login}">
    Change Name: <br/>
    <input type="text" name="name">
    <input type="submit" name="button" value="Change name"/> <br/>
    Change Surname: <br/>
    <input type="text" name="surname">
    <input type="submit" name="button" value="Change surname"/> <br/>
    Change Password: <br/>
    <input type="text" name="password">
    <input type="submit" name="button" value="Change Password"/> <br/><br/><br/>
    <input type="submit" name="button" value="Delete account" /> <br/><br/>
</form>

</body>
</html>