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
        <title>Login Page</title>
    </head>
    <style>
        p {
            font-size: 18px
        }
       
    </style>
    <body>
    <h1>Login:</h1>
        <form method= "post" action="j_security_check" > 
            <p> username : <input type="text"  name= "j_username" ></p>
            <p> password: <input type="password"  name="j_password" ></p>
            <input class='button' type="submit" name="knop" value="login"/>
        </form>    
    </body>
</html>
