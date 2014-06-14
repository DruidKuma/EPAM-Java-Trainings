
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${lang.orderDrink}</title>
        <link href="../resources/css/style.css" rel="stylesheet" type="text/css" >
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div id="contents">
            <h3>${lang.chooseDrink}</h3>
            <form method='POST' action='confirm'>
                <select name='drink'>
                    <c:forEach var="drink" items="${drinks}">
                        <option value="${drink.title}"><c:out value="${lang[drink.title]}"/> 
                    </c:forEach>
                </select>
                <h3>${lang.addIngr}:</h3>
                <table id="order">
                <c:forEach var="ingr" items="${ingrs}">
                    <tr>
                        <td>${lang[ingr.title]}</td>
                        <td>0<input type="range" name="${ingr.title}" max="50" value='0' step='5'>50</td>
                    <tr>
                </c:forEach>
                </table>
                <input type='submit' value='${lang.order}'>
            </form>
        </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>
