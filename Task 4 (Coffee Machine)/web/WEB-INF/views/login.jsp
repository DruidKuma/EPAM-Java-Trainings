
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${lang.welcome}</title>
        <link href="../resources/css/style.css" rel="stylesheet" type="text/css" >
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div id="contents">
            <h3>${lang[error]}:</h3>
            <form class="login" method='POST' action='login'>
                <p><input type='text' name='login' value='' placeholder='${lang.login}'></p>
                <p><input type='password' name='password' value='' placeholder='${lang.password}'</p>
                <p><input type='submit' value='${lang.enter}'></p>
            </form>
        </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>
