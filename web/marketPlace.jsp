<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>USER</title>
</head>

<body>

    <H1> Hello, ${login} </H1> <br/>

    <c:if test="${isPay == true}">
        payment was successful!<br/><br/>
    </c:if>

    <c:if test="${isNotPay == true}">
        payment failed!<br/><br/>
    </c:if>

        <table border='2' width='80%'>

            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Price</th>
                <th> </th>
            </tr>

            <c:forEach items="${goods}" var="good">

                <tr>
                    <td><c:out value="${good.id}"/></td>
                    <td><c:out value="${good.name}"/></td>
                    <td><c:out value="${good.description}"/></td>
                    <td><c:out value="${good.price}"/></td>
                    <td><a href='edit?id=${good.id}'>Edit!</a></td>
                </tr>
        </c:forEach>
    </table>


</body>
</html>