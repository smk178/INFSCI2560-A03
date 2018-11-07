<%-- 
    Document   : index
    Created on : Nov 2, 2018, 6:48:00 PM
    Author     : SMK178
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Assignment Three</title>
    </head>
    <body>
        <h1>Welcome to Assignment Three!</h1>
        <c:if test="${not empty username}">
            <h4>Welcome <strong>${username}</strong>!</h4>
        </c:if>
        <p>We are going to use Java Web Project with Beans and Servlet to serve information form Derby Database.</p>
        <ul>
            <li><c:url value="/Register" context="/Assignment.Three" var="register"></c:url><a href="${register}"/>Register</li>
            <li><c:url value="/Login" context="/Assignment.Three" var="login"></c:url><a href="${login}"/>Login</li>
        </ul>
    </body>
</html>
