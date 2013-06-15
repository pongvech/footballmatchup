<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Football Match Up</title>
    </head>
    <body>
        <div id="header">
            <h2>
                <a href="<spring:url value='index.html' />">
                    Football Match Up
                </a>
            </h2>
            <div>
                <a href="<spring:url value='register.html' />" >Register</a>
            </div>
        </div>
        <div id="content">
            <jsp:include page="${pageContent}.jsp" />
        </div>
    </body>
</html>