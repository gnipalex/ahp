<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="nav navbar-nav navbar-right">
    <s:authorize access="isAuthenticated()">
        <li><a href="#"><span class="glyphicon glyphicon-user"></span> <s:authentication property="principal.username" /></a></li>
        <li><a href="${ctx}/logout" class=""><span class="glyphicon glyphicon-log-out"></span> Sign out</a></li>
    </s:authorize>
    <s:authorize access="isAnonymous()">
        <li><a href="${ctx}/register"><span class="glyphicon glyphicon-plus"></span> Register</a></li>
        <li><a href="${ctx}/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </s:authorize>
    <li></li>
</ul>
