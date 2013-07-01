<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<ul class="thumbnails">
    <c:forEach items="${matchCardDisplayList}" var="match">
        <li class="span4">
            <div class="thumbnail ${match.cardColor} matchcard">
                <h2>${match.match.name} <span class="badge badge-info matchcard_playercount">${match.playerCount}</span></h2>
                <h4>Date/Time : <joda:format value="${match.match.playTime}" pattern="${formatDateTime}" /></h4>
                <h4>Venue : ${match.match.place}</h4>
                <p>
                    <a href="${match.buttonLink}" class="btn btn">${match.buttonName}</a>
                    <a href="<spring:url value='/match/info/${match.match.id}' />" class="btn">More Detail </a>
                </p>
            </div>
        </li>
    </c:forEach>
</ul>

