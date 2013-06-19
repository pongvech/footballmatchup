<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<div class="span12">
    <div>
        <form action="<spring:url value='/match/join/${match.id}' />" method="post">
            <input type="submit" value="Join" class="btn" />
        </form>
    </div>
    <c:if test="${match.detail != null}">
        <div>
            ${match.detail}
        </div>
    </c:if>
    <div>
        ${match.place}
    </div>
    <div>
        <joda:format value="${match.createdTime}" pattern="${formatDateTime}" />
    </div>
</div>