<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<div class="span12">
    <c:if test="${playerMatch == null}">
        <div>
            <a href="<spring:url value='/match/join/${match.id}' />" class="btn">
                Join
            </a>
        </div>
    </c:if>
    <div>
        Creator : ${match.creator.username}
    </div>
    <c:if test="${match.detail != null}">
        <div>
            Detail : ${match.detail}
        </div>
    </c:if>
    <div>
        Where : ${match.place}
    </div>
    <div>
        When : <joda:format value="${match.playTime}" pattern="${formatDateTime}" />
    </div>
</div>