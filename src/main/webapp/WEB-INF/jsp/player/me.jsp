<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="stylesheet" href="<spring:url value='/assets/css/morris/style.css' />">
<script src="<spring:url value='/assets/js/morris/raphael.js' />"></script>
<script src="<spring:url value='/assets/js/morris/morris.js' />"></script>

<div class="span12 matchcard-needlogin matchcard">
    <script type="text/javascript" src="<spring:url value='/assets/js/chart.js' />"></script>
    <script type="text/javascript" src="<spring:url value='/assets/js/me.js' />"></script>
    <h2>${pageTitle}</h2>
    <div id="myfirstchart" style="height: 250px;"></div>
</div>
