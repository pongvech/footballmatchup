<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

    <ul class="thumbnails">
    <c:forEach items="${matchList}" var="match">
        <li class="span4">
            <div class="thumbnail">
                <h2><a href="<spring:url value='/match/info/${match.id}' />">${match.name}</a></h2>
                <h4>Date/Time : <joda:format value="${match.playTime}" pattern="${formatDateTime}" /></h4>
                <h4>Venue : ( ${match.place} )</h4>
                <p><a href="#" class="btn btn">Join</a></p>
            </div>
        </li>
    </c:forEach>
    </ul>

