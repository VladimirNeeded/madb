<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>PAYMENT</title>
</head>
<body>
<c:if test="${isPay == true}">
    Payment was successful!<br/><br/>
</c:if>
<c:if test="${isNotPay == true}">
    Payment failed!<br/><br/>
</c:if>
</body>
</html>
