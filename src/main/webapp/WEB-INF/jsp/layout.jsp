<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Football Match Up</title>
        <link rel="stylesheet" type="text/css" href="<spring:url value="/assets/bootstrap/css/bootstrap.min.css" />" />
        <link rel="stylesheet" type="text/css" href="<spring:url value="/assets/css/style.css" />" />
    </head>
    <body>
        <div class="container">
            <div id="header" class="row">
                <div class="span6">
                    <h1>
                        <a href="<spring:url value='index.html' />">
                            Football Match Up
                        </a>
                    </h1>
                </div>
                <div class="span6">
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
            </div>
            <div id="content" class="row">
                <jsp:include page="${pageContent}.jsp" />
            </div>
        </div>
    </body>
</html>