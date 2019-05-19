<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>USER</title>
</head>

<body>

    <H1> Hello, ${login} </H1> <br/>

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
                    <td><a href='buy?id=${good.id}'>Buy!</a></td>
                </tr>
        </c:forEach>
    </table>


</body>
</html>