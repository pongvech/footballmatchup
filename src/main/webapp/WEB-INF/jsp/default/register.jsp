<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div>
    <form:form commandName="player">
        <div>
            <form:errors path="*" />
        </div>
        <div>
            Email : <form:input path="email" />
        </div>
        <div>
            Username : <form:input path="username" />
        </div>
        <div>
            Password : <form:password path="password" />
        </div>
        <div>
            <input type="submit" value="Register" />
        </div>
    </form:form>
</div>