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
    <body class="container">
        <div class="row">
            <div id="header" class="span12">
                <div class="row">
                    <div class="span6">
                        <h1>
                            <a href="<spring:url value='/index.html' />">
                                Football Match Up
                            </a>
                        </h1>
                    </div>
                    <div class="span6" id="user-control">
                        <c:choose>
                            <c:when test="${loggedInPlayer != null}">
                                <div>
                                    ${loggedInPlayer.username} |
                                    <a href="<spring:url value='/logout.html' />" >Logout</a>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div>
                                    <a href="<spring:url value='/login.html' />" >Login</a> &bull;
                                    <a href="<spring:url value='/register.html' />" >Register</a>
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div id="nav" class="span12">
                <ul class="nav nav-tabs">
                    <li>
                        <a href="<spring:url value='/index.html' />">Home</a>
                    </li>
                    <li><a href="<spring:url value='/match/index.html' />">Match</a></li>
                </ul>
            </div>
        </div>
        <div class="row">
            <div id="content" class="span12">
                <div class="row">
                    <jsp:include page="${pageContent}.jsp" />
                </div>
            </div>
        </div>
        <div class="row">
            <div id="footer" class="span12">
                <div class="row">
                    <div class="span12">
                        Football Match Up
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>