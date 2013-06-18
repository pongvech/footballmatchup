<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="span12">
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
            <label class="control-label">Date Time</label>
            <div class="controls">
                <form:input path="dateTime" placeholder="Date Time" cssClass="span3" value="31/05/2013 19:00" />
                <form:errors path="dateTime" cssClass="text-error" />
            </div>
        </div>
        <div class="control-group">
            <div class="controls span3">
                <input type="submit" value="Create" class="btn btn-success btn-block" />
            </div>
        </div>
    </form:form>
</div>