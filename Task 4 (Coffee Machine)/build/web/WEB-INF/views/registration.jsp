
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${lang.registerTitle}</title>
        <link href="../resources/css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <%@ include file="header.jsp" %>
        <div id="contents">
            <p class="info">${lang[infoMessage]}</p>
            <form method="POST" action="registration">
                <p><input type='text' name='login' value='' placeholder='${lang.login}'> ${lang[loginErr]}</p>
                <p><input type='password' name='password' value='' placeholder='${lang.password}'></p>
                <p><input type='password' name='verification' value='' placeholder='${lang.checkPass}'>${lang[passErr]}</p>
                <p><input type='text' name='name' value='' placeholder='${lang.name}'></p>
                <p><input type='text' name='surname' value='' placeholder='${lang.surname}'></p>
                <p><input type='text' name='email' value='' placeholder='${lang.email}'>${lang[emailErr]}</p>
                <p><input type='submit' value='${lang.submit}'></p>
            </form>
        </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>
