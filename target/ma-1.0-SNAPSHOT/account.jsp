<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>account</title>
</head>

<body>

    <H1> Hello, ${login} </H1> <br/>

    <form action="account" method="post">
        <input type="hidden" name="login" value="${login}">
        Change Password: <br/>
        <input type="text" name="new_password">
        <input type="submit" name="button" value="Change Password"/> <br/><br/><br/>
        <input type="submit" name="button" value="Delete account" />

    </form>


</body>
</html>
