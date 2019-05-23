<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>


<form action="editGood" method="post">

    <input type="hidden" name="value" value="${value}">
    <input type="hidden" name="id" value="${id}">
    Change Name: <br/>
    <input type="text" name="name">
    <input type="submit" name="button" value="Change name"/> <br/>
    <c:if test="${changeName == true}">
        Name was changed!<br/><br/>
    </c:if>
    Change Description: <br/>
    <input type="text" name="Description">
    <input type="submit" name="button" value="Change Description"/> <br/>
    <c:if test="${changeDescription == true}">
        Description was changed!<br/><br/>
    </c:if>
    Change Price: <br/>
    <input type="text" name="Price">
    <input type="submit" name="button" value="Change Price"/><br/>
    <c:if test="${changePrice == true}">
        Price was changed!<br/><br/>
    </c:if><br/><br/><br/>
</form>

</body>
</html>