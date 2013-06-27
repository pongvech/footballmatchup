<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Football Match Up</title>
        <link rel="stylesheet" type="text/css" href="<spring:url value='/assets/css/bootstrap-datetimepicker.min.css' />" />
        <link rel="stylesheet" type="text/css" href="<spring:url value='/assets/css/bootstrap/css/bootstrap.min.css' />" />
        <link rel="stylesheet" type="text/css" href="<spring:url value='/assets/css/bootstrap/css/collapse.min.css' />" />
        <link rel="stylesheet" type="text/css" href="<spring:url value='/assets/css/myStyle.css' />" />

        <script type="text/javascript" src="<spring:url value='/assets/js/jquery/jquery-2.0.2.min.js' />"></script>
        <script type="text/javascript" src="<spring:url value='/assets/css/bootstrap/js/collapse.min.js' />"></script>
        <script type="text/javascript" src="<spring:url value='/assets/js/jquery-ui/js/jquery-ui-1.10.3.custom.min.js' />"></script>
        <script type="text/javascript" src="<spring:url value='/assets/js/bootstrap-datetimepicker.min.js' />"></script>
        <style>
              body {
                padding-top: 60px; /* 60px to make the container go all the way to the bottom of the topbar */
              }
            </style>
    </head>
    <body>

        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="brand" href="<spring:url value='/' />">FootballMatchUp!</a>
                    <c:choose>

                        <c:when test="${loggedInPlayer != null}">
                            <div class="navbar-form pull-right">
                                <a href="<spring:url value='/register?edit=true' />" class="btn btn"><i class="icon-user"></i> ${loggedInPlayer.username} </a>
                                <a href="<spring:url value='/logout' />" class="btn btn-danger"><i class="icon-off"></i> Logout </a>
                            </div>
                        </c:when>

                        <c:otherwise>
                            <form action="<spring:url value="/login" />" method="post" class="navbar-form pull-right">
                                <input class="span2" type="text" name="emailOrUsername" placeholder="Email Or Username">
                                <input class="span2" type="password" name="password" placeholder="Password">
                                <button type="submit" class="btn btn-primary"><i class="icon-circle-arrow-right"></i> Sign in </button>
                                <a class="btn btn-info" href="<spring:url value="/register" />"><i class="icon-book"></i> Register </a>
                                <!--<a href="<spring:url value='/register' />" >Register</a>-->
                            </form>
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