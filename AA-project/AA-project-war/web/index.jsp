<%-- 
    Document   : index
    Created on : 13-nov-2019, 15:01:20
    Author     : r0631103
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css">
        <title>Index Page</title>
    </head>
    <style>
    .button {
        background-color: blue;
        border: none;
        color: white;
        padding: 15px 32px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
    }
    </style>
    <body>
        
        <form method="post" action="controller.do" >
            <input class="button" style="font-size:20px" type="submit" name="knop" value="logout"/> 
        </form>

         <form method="post" action="controller.do">
        <h1>Welkom </h1>
        <p>Wat wil je doen </p>
        <input class="button" style="font-size:20px" type="submit" name="knop" value="Overzicht"/> 
        </form>
    </body>
</html>
