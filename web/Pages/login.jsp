<%-- 
    Document   : login
    Created on : Nov 2, 2018, 7:42:37 PM
    Author     : SMK178
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login to Assignment Three!</h1>
        <hr/>
        <c:if test="${not empty message}">
            <h1 style="color:red;">${message}</h1>
        </c:if>
            <form method="post" action="Login">
            <label>Username: 
                <input type="text" name="username"/>
            </label><br/><br/>
            <label>Password: 
                <input type="text" name="password"/>
            </label><br/><br/>
            <button type="submit">Login</button>
        </form>
    </body>
</html>
