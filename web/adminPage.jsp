<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ADMIN</title>
</head>
<body>
<H1> Hello, admin ${login} </H1> <br/>


    <form action="adminPage" method="post">
         <br/><br/>
        <H3>Goods:</H3><br/>
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
                    <td><c:out value="${good.id}"/><input type="hidden" name="id" value="${good.id}"></td>
                    <td><c:out value="${good.name}"/></td>
                    <td><c:out value="${good.description}"/></td>
                    <td><c:out value="${good.price}"/></td>
                    <td> <input type="submit" name="button"  value="Edit"/></td>
                </tr>
            </c:forEach>
        </table><br/><br/>
    </form>

    <form action="adminPage" method="post">

            <H3>Users in system:</H3><br/>
        <table border='2' width='80%'>

            <tr>
                <th>ID</th>
                <th>Login</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Email</th>
                <th>EDIT!</th>
            </tr>

            <c:forEach var="user" items="${list}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.surname}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><input type="submit" name="button"  value="${user.getLogin()}"/>
                </td> <br/></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</body>
</html>
