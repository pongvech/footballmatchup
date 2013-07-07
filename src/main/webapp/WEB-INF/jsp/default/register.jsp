<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="span12 matchcard-needlogin matchcard">
    <h2>${pageTitle}</h2>
    <form:form commandName="registerForm" cssClass="form-horizontal">
        <div class="control-group">
            <label class="control-label">Email</label>
            <div class="controls">
                <form:input path="email" placeholder="Email" cssClass="span3" />
                <form:errors path="email" cssClass="text-error" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Username</label>
            <div class="controls">
                <form:input path="username" placeholder="Username" cssClass="span3" />
                <form:errors path="username" cssClass="text-error" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Password</label>
            <div class="controls">
                <form:password path="password" placeholder="Password" cssClass="span3" />
                <form:errors path="password" cssClass="text-error" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Confirm Password</label>
            <div class="controls">
                <form:password path="confirmPassword" placeholder="Confirm Password" cssClass="span3" />
                <form:errors path="confirmPassword" cssClass="text-error" />
            </div>
        </div>
        <div class="control-group">
            <div class="controls span3">
                <input type="submit" value="Register" class="btn btn-success btn-block" />
            </div>
        </div>
    </form:form>
</div>