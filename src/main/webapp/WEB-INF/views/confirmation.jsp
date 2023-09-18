<%-- 
    Document   : confirmation
    Created on : Sep 17, 2023, 8:26:59 PM
    Author     : David
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>City Successfully Created</title>
    </head>
    <body>
        <h1>Confirmation of City Creation</h1>
        <ul>
            <li>${requestScope.city.ID}</li>
            <li>${requestScope.city.name}</li>
            <li>${requestScope.city.district}</li>
            <li>${requestScope.city.population}</li>
            
        </ul>
    </body>
</html>
