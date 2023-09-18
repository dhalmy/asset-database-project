<%-- 
    Document   : city
    Created on : Sep 13, 2023, 11:19:45 AM
    Author     : David
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create A City</title>
    </head>
    <body>
        <h1>Create A City</h1>
        
        <c:if test="${not empty requestScope.violations}">
            <p>There were issues with your submission. Please fix them and submit the form again.</p>
            <ul>
                <c:forEach items="${requestScope.violations}" var="violation">
                    <li>
                        There was a problem with ${violation.propertyPath}. The error message was ${violation.message}.
                    </li>
            </c:forEach>
            </ul>
            
        </c:if>
              

        <form method="post" action="/dhalmy-fp/city">
            <div>
                <label for="cityID">City ID</label>
                <input type="text" id ="cityID" name="cityID" value="${requestScope.city.ID}"/> 
            </div>
            <div>
                <label for="cityName">City Name</label>
                <input type="text" id ="cityName" name="cityName" value="${requestScope.city.name}"/> 
            </div>

            <!--country code omitted due to it being a FK and there being hundreds of country codes-->
            <!--            <div>
                            <label for="countryCode">Country Code</label>
                            <input type="text" id ="countryCode" name="countryCode"/>
                        </div>-->

            <div>
                <label for="district">District</label>
                <input type="text" id ="district" name="district" value="${requestScope.city.district}"/> 
            </div>
            <div>
                <label for="population">Population</label>
                <input type="text" id ="population" name="population" value="${requestScope.city.population}"/> 
            </div>


            <button type="submit">Create City</button>
        </form>
    </body>
</html>
