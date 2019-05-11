<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>USER</title>
</head>

<body>

    <H1> Hello, ${login} </H1> <br/>

    <form action="userPage" method="post">
        <input type="hidden" name="login" value="${login}">
    </form>


</body>
</html>
