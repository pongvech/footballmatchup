<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="span12">
    <form:form commandName="loginForm" cssClass="form-horizontal">
        <div class="control-group">
            <label class="control-label">Email or Username</label>
            <div class="controls">
                <form:input path="emailOrUsername" placeholder="Email or Username" cssClass="span3" />
                <form:errors path="emailOrUsername" cssClass="text-error" />
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">Password</label>
            <div class="controls">
                <form:input path="password" placeholder="Password" cssClass="span3" />
                <form:errors path="password" cssClass="text-error" />
            </div>
        </div>
        <div class="control-group">
            <div class="controls span3">
                <label class="checkbox inline"> <form:checkbox path="rememberMe" id="chkRememberMe" /> Remember Me</label>
            </div>
        </div>
        <div class="control-group">
            <div class="controls span3">
                <input type="submit" value="Login" class="btn btn-success btn-block" />
            </div>
        </div>
    </form:form>
</div>