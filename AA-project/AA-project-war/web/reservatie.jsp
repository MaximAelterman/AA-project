<%-- 
    Document   : Reservatie
    Created on : 17-dec-2019, 22:15:22
    Author     : r0631
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservatie Page</title>
    </head>
    <body>
        <h1>Hier komt reservatie pagina</h1>
        <form method="post" action=<c:url value="/controller.do"/>>
            <input type="submit" name="knop" value="Overzicht"/>
        </form>
        <p> ik wil een reservatie maken met machine ${machine.mnr} met naam ${machine.mnaam}</p>
    </body>
</html>
<%@include file="footer.jsp"%>

