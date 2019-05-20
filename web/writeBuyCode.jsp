<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Write code</title>
</head>
<body>
<H1> Type your code: </H1> <br/>

<center>

    <form action="/buy" method="post">

        <input type="hidden" name="good_id"  value="${good_id}">
        <input type="password" title="code" name="code">
        <input type="submit">

    </form>
</center>

</body>
</html>
