<%-- 
    Document   : register
    Created on : Nov 2, 2018, 6:51:53 PM
    Author     : SMK178
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Account</title>
    </head>
    <body>
        <h1>Register Free Account for Assignment Three!</h1>
        <h3>Instructions:</h3>
        <p>Registration are subject to password validation (confirm password shall match with the password), if the password do not match the submitted form is stored in the session and returned the current registration form once again with prefilled data.</p>
        <hr/>
        <c:if test="${not empty message}">
            <h4 style="color:red;">${message}</h4>
        </c:if>
        <c:if test="${not empty registration}">
            <h3 style="color: red;">Password Does Not Match</h3>
        </c:if>
        <form method="post">
            <c:choose>
                <c:when test="${empty registration}">
                    <label>Username: 
                        <input type="text" name="username"/>
                    </label><br/><br/>
                    <label>Password: 
                        <input type="text" name="password"/>
                    </label><br/><br/>
                    <label>Confirm Password: 
                        <input type="text" name="confirm"/>
                    </label><br/><br/>
                </c:when>
                <c:otherwise>
                    <label>Username: 
                        <input type="text" name="username" value="${registration.username}"/>  
                    </label><br/><br/>
                    <label>Password:         
                        <input type="text" name="password" value="${registration.password}"/>
                    </label><br/><br/>
                    <label>Confirm Password: 
                        <input type="text" name="confirm" value="${registration.confirm}"/>
                    </label><br/><br/>
                </c:otherwise>
            </c:choose>
            <button type="submit">Create</button>
        </form>
    </body>
</html>
