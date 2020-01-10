<%-- 
    Document   : error
    Created on : 13-nov-2019, 16:05:55
    Author     : r0631103
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Fout pagina</title>
    </head>
    <body>
        <h1>Er is een fout opgetreden tijdens het inloggen.</h1>
        <br>
         <form method="post" action=<c:url value="/controller.do"/>>
             <input type="submit" name="knop" value="Login"/>
         </form>

    </body>
</html>
