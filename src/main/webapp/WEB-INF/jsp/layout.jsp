<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Football Match Up</title>
        <link rel="stylesheet" type="text/css" href="<spring:url value='/assets/css/bootstrap/css/bootstrap.min.css' />" />
        <link rel="stylesheet" type="text/css" href="<spring:url value='/assets/css/bootstrap/css/bootstrap-responsive.css' />" />
        <link rel="stylesheet" type="text/css" href="<spring:url value='/assets/js/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css' />" />
        <link rel="stylesheet" type="text/css" href="<spring:url value='/assets/css/myStyle.css' />" />
        <script type="text/javascript" src="<spring:url value='/assets/js/jquery/jquery-2.0.2.min.js' />"></script>
        <script type="text/javascript" src="<spring:url value='/assets/css/bootstrap/js/bootstrap.min.js' />"></script>
        <script type="text/javascript" src="<spring:url value='/assets/js/chart.js' />"></script>
        <script type="text/javascript" src="<spring:url value='/assets/js/app.js' />"></script>
        <style>
            @media (min-width: 980px) {
            body {
                padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
            }
            }
        </style>
    </head>
    <body>

        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="brand" href="<spring:url value='/' />">FootballMatchUp!</a>
                    <c:choose>
                        <c:when test="${loggedInPlayer != null}">
                            <div class="nav-collapse collapse">
                                <div class="navbar-form pull-right">
                                    <a href="<spring:url value='/player/edit' />" class="btn btn"><i class="icon-user"></i> ${loggedInPlayer.username} </a>
                                    <a href="<spring:url value='/player/me' />" class="btn btn">Stat</a>
                                    <a href="<spring:url value='/logout' />" class="btn btn-danger"><i class="icon-off"></i> Logout </a>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="nav-collapse collapse">
                                <form action="<spring:url value="/login" />" method="post" class="navbar-form pull-right">
                                    <input class="span2" type="text" name="emailOrUsername" placeholder="Email Or Username">
                                    <input class="span2" type="password" name="password" placeholder="Password">
                                    <button type="submit" class="btn btn-primary"><i class="icon-circle-arrow-right"></i> Sign in </button>
                                    <a class="btn btn-info" href="<spring:url value="/register" />"><i class="icon-book"></i> Register </a>
                                    <!-- <a class="btn btn-info" href="https://www.facebook.com/dialog/oauth?client_id=517466198319051&redirect_uri=${baseUrl}/login/fb&scope=email">Login with Facebook</a> -->
                                </form>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="container">
            <c:if test="${not empty alert}">
                <div class="row">
                    <div class="span12">
                        <div class="${alertCss}">${alert}</div>
                    </div>
                </div>
            </c:if>
            <div class="row">
                <jsp:include page="${pageContent}.jsp" />
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div id="footer" class="span12 muted white">
                  <h6>&copy; FootballMatchUp! 2013</h6>
                </div>
            </div>
        </div>

    </body>
</html>
