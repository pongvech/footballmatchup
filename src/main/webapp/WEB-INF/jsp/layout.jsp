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
            <c:choose>
                <c:when test="${loggedInPlayer != null}">
                    <div>
                        Welcome, ${loggedInPlayer.username} |
                        <a href="<spring:url value='logout.html' />" >Logout</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div>
                        <a href="<spring:url value='register.html' />" >Register</a> |
                        <a href="<spring:url value='login.html' />" >Login</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
        <div id="content">
            <jsp:include page="${pageContent}.jsp" />
        </div>
    </body>
</html>