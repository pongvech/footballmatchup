<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

<div class="span12">
    <div>
        <a href="<spring:url value='/match/create' />">
            Create new Match
        </a>
    </div>
    <c:forEach items="${matchList}" var="match">
        <div>
            <a href="<spring:url value='/match/info/${match.id}' />">
                ${match.name}
            </a>
            <joda:format value="${match.dateTime}" pattern="${formatDateTime}" />
        </div>
    </c:forEach>
</div>