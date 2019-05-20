<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>ADMIN</title>
</head>
<body>
<H1> Hello, admin ${login} </H1> <br/>

    <form action="adminPage" method="post">
        <input type="submit" name="button" value="Edit my account" /><input type="hidden" name="login" value="${login}"> <br/><br/>
            Users in system: <br/>
        <table>
            <c:forEach var="user" items="${list}">
                <tr>
                    <td>${user} <td align="left"> Edit account <input type="submit" name="button"  value="${user.getLogin()}" />
                    </td> <br/></td>
                </tr>
            </c:forEach>
        </table>
    </form>
</body>
</html>
