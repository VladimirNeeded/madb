<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8" />
    <title>Title</title>
</head>
<body>
    <c:if test="${isRegistered == true}">
        You successfully registered!<br/><br/>
    </c:if>

    <c:if test="${isLogin == false}">
        Login or password are not valid!<br/><br/>
    </c:if>


  <form action="SignIn" method="post" >
      Login <input type="text" name="login">
      Password <input type="password" name="password">
      <input type="submit" name="choice" value="Sign_in"/>
  </form>

</body>
</html>