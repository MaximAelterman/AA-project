<%-- 
    Document   : bevestig
    Created on : 5-jan-2020, 21:24:05
    Author     : r0631
--%>

<%@page import="java.math.BigDecimal"%>
<%@page import="java.math.BigInteger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import ="beans.Momenten" %>
<% Momenten Res = (Momenten)session.getAttribute("Res");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservaties bevestigingspagina</title>
    </head>
    <body>
        <h1>Kan u uw reservaties bevestigen aub</h1>
        <% 
            double prijs = Res.getMnr().getHuurprijs().doubleValue();
            double duur = Res.getDuur().doubleValue();
            double totalprijs= prijs*duur;  
        %>
   
         <c:if test="${groep == 'Extern'}">
             <p>De huurprijs voor de reservatie bedraagt <%=totalprijs%> euro </p>
         </c:if>
        <form method= "post" action=<c:url value="/controller.do"/> >
            <input type="hidden" name="momid" value="${Res.momid}"/>
            <input type="submit" name="knop" value="Reserveer moment"/>
        </form>
        <form method= "post" action=<c:url value="/controller.do"/> >
            <input type="hidden" name="details" value="${machine.mnr}"/>
            <input type="submit" name="knop" value="Reserveer"/>
        </form>


    </body>
</html>
