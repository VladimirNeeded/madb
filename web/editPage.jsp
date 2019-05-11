<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <c:if test="${changeName == true}">
        Name was changed!<br/><br/>
    </c:if>

    Change Surname: <br/>
    <input type="text" name="surname">
    <input type="submit" name="button" value="Change surname"/> <br/>
    <c:if test="${changeSurname == true}">
        Surname was changed!<br/><br/>
    </c:if>
    Change Password: <br/>
    <input type="text" name="password">
    <input type="submit" name="button" value="Change Password"/><br/>
    <c:if test="${changePassword == true}">
        Password was changed!<br/><br/>
    </c:if><br/><br/><br/>
    <input type="submit" name="button" value="Delete account" />
    <c:if test="${deleteAccount == true}">
        Account deleted!<br/><br/>
    </c:if><br/><br/>
</form>

</body>
</html>