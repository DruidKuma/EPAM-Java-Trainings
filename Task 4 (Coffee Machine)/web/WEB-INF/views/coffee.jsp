
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${lang.drinkCoffee}</title>
        <link href="../resources/css/style.css" rel="stylesheet" type="text/css" >
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div id="contents">
            <h3>${lang.welcomeCoffee}, ${user.name} ${user.surname}</h3>
            <h3>${lang.youHave} ${user.account}$ ${lang.onAccount}</h3>
        </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>
