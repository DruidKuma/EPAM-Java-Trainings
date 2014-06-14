
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="my" uri="/WEB-INF/tlds/custom-tags.tld"%>
<!DOCTYPE html>
<div id="header">
    <div>
        <div id="logo"></div>
        <ul id="navigation">
            <my:access anon="true">
                <li><a href="login">${lang.loginLink}</a></li>
                <li><a href="registration">${lang.registerLink}</a></li>
            </my:access>
            
            <my:access admin="true">
                <li><a href="load">${lang.loadLink}</a></li>
            </my:access>
            
            
            <my:access user="true">
                <li><a href="order">${lang.orderLink}</a></li>
                <li><a href="coffee">${lang.cabinetLink}</a></li>
                <li><a href="logout">${lang.logoutLink}</a></li>
            </my:access>      
        </ul>
    </div>
</div>