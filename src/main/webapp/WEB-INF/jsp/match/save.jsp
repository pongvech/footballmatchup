<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="span12 matchcard-needlogin matchcard">
    <c:if test="${pageTitle != null}">
            <h2>${pageTitle}</h2>
    </c:if>
    <form:form commandName="match" cssClass="form-horizontal">
        <div class="control-group">
            <label class="control-label">Name</label>
            <div class="controls">
                <form:input path="name" placeholder="Name" cssClass="span3" />
                <form:errors path="name" cssClass="text-error" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Detail</label>
            <div class="controls">
                <form:textarea path="detail" placeholder="Detail" cssClass="span3" />
                <form:errors path="detail" cssClass="text-error" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Place</label>
            <div class="controls">
                <form:input path="place" placeholder="Place" cssClass="span3" />
                <form:errors path="place" cssClass="text-error" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Play time</label>
            <div class="controls">
                <form:input path="playTime" placeholder="Play time" cssClass="span3" />
                <form:errors path="playTime" cssClass="text-error" />
            </div>
        </div>
        <div class="control-group">
            <div class="controls span3">
                <c:if test="${match.id != null}">
                    <form:hidden path="id" />
                    <form:hidden path="createdTime" />
                    <form:hidden path="creator.id" />
                </c:if>
                <input type="submit" value="${btnSubmitValue}" class="btn btn-success btn-block" />
            </div>
        </div>
    </form:form>
</div>