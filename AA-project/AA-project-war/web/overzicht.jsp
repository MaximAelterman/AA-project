<%-- 
    Document   : overzicht
    Created on : 13-nov-2019, 16:10:49
    Author     : r0631103
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Dit is een overzichtspagina</h1>
        <p>dit is test </p>
        
        <div class="container">
            <c:forEach var="machine" items="${applicationScope.machines}">
                <div class='info'>
                    <h3>${machine.mnaam}</h3>
                    <p>Lokaal: ${machine.mloc}</p>
                    <p>Opleiding: ${machine.opleiding}</p>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
