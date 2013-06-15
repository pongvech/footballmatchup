<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
    <form:form commandName="loginForm">
        <div>
            <form:errors path="*" />
        </div>
        <div>
            Email or Username : <form:input path="emailOrUsername" />
        </div>
        <div>
            Password : <form:password path="password" />
        </div>
        <div>
            <form:checkbox path="rememberMe" id="chkRememberMe" />
            <label for="chkRememberMe">Remember Me</label>
        </div>
        <div>
            <input type="submit" value="Register" />
        </div>
    </form:form>
</div>