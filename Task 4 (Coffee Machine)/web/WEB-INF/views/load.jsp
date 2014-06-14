
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${lang.loadMachine}</title>
        <link href="../resources/css/style.css" rel="stylesheet" type="text/css" >
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div id="contents">
            <p class="info">${lang[infoMessage]}</p>
            <table id="loadIngr">
                <tr>
                    <th>${lang.title}</th>
                    <th>${lang.amount}</th>
                </tr>
                <c:forEach var="ingr" items="${ingrs}">
                    <tr>
                        <td>${lang[ingr.title]}</td>
                        <td>${ingr.amount}</td>
                        <td>
                            <c:choose>
                                <c:when test="${amountInput == ingr.title}">
                                    <form method="POST" action="load">
                                        <input type="hidden" name="ingr" value="${ingr.title}">
                                        <input type="text" name="amount" size="4">
                                        <input type="submit" value="${lang.add}">
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form method="POST" action="load">
                                        <input type="hidden" name="ingr" value="${ingr.title}">
                                        <input type="submit" value="${lang.load}">
                                    </form>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>
