<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<div class="span12">
    <div class="pull-right">
        <a href="<spring:url value='/match/create' />" class="btn">
            Create new Match
        </a>
    </div>
    <div class="pull-left">
        <c:forEach items="${matchList}" var="match">
            <div class="match-box">
                <div>
                    <a class="title" href="<spring:url value='/match/info/${match.id}' />">
                        ${match.name}
                    </a>
                </div>
                <div>
                    <joda:format value="${match.playTime}" pattern="${formatDateTime}" />
                </div>
            </div>
        </c:forEach>
    </div>

</div>