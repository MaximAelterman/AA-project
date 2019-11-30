<%-- 
    Document   : login
    Created on : 13-nov-2019, 16:33:19
    Author     : r0631103
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        p {
            font-size: 18px
        }
        .button {
            background-color: blue;
            border: none;
            color: white;
            padding: 15px 32px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 22px;
            margin: 4px 2px;
        }
    </style>
    <body>
    <h1>Login:</h1>
<!--        <form method= "post" action="j_security_check" > -->
         <form method= "post" action=<c:url value="/controller.do"/>>
            <p> username : <input type="text"  name= "username" ></p>
            <p> password: <input type="password"  name="password" ></p>
            <input class='button'   type="submit" name="knop" value="login"/>
        </form>    
    </body>
</html>
