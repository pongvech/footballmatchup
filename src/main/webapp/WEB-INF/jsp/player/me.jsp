<link rel="stylesheet" href="http://cdn.oesmith.co.uk/morris-0.4.3.min.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
<script src="http://cdn.oesmith.co.uk/morris-0.4.3.min.js"></script>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="span12 matchcard-needlogin matchcard">
    <script type="text/javascript" src="<spring:url value='/assets/js/chart.js' />"></script>
    <script type="text/javascript" src="<spring:url value='/assets/js/me.js' />"></script>
    <h2>${pageTitle}</h2>
    <div id="myfirstchart" style="height: 250px;"></div>
</div>
