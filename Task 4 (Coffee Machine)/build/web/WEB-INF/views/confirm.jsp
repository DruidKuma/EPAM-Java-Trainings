
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${lang.orderConfirm}</title>
        <link href="../resources/css/style.css" rel="stylesheet" type="text/css" >
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div id="contents">
            <p class="order">
                ${lang.youOrdered}: ${lang[drink.title]}
                <c:forEach var="ingr" items="${drink.ingrs}">
                    <c:if test="${ingr.amount > 0}">
                        <br>
                        ${lang.with} ${ingr.amount}${lang.grOf} ${lang[ingr.title]} 
                    </c:if>
                </c:forEach>
            </p>
            <p class="info">Total cost: <span id="totalPrice">${drink.totalPrice}$</span></p>
            <p>${lang[errorMoney]}</p>
            <p>${lang[errorSupply]}</p>

            <c:if test="${not empty accept}">
            <form method="POST" action="buy">   
                <input type="submit" value="${lang.confirm}">
            </form>
            </c:if>

            <form method="POST" action="order">
                <input type="submit" value="${lang.returnAction}">
            </form>
        </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>
