
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="footer">
    <!-- Buttons for language change -->
    <ul class="navigation">
	<li>
            <form method="POST" action="changelang">
                <input type="hidden" name="language" value="en">
                <input type="submit" value="English">
            </form>
        </li>
        <li>
            <form method="POST" action="changelang">
                <input type="hidden" name="language" value="ru">
                <input type="submit" value="Русский">
            </form>
        </li>
    </ul>
    <!-- Copyright info -->
    <p id="footnote">${lang.copyright}</p>
</div>
