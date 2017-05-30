<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="header" tagdir="/WEB-INF/tags/header" %>

<nav class="navbar navbar-default  navbar-static-top">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="${ctx}" title="Decision Support System - AHP (analytic hierarchy process)">DSS AHP</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse" aria-expanded="false" style="height: 1px;">
      <ul class="nav navbar-nav">
        <li><a href="#about">About</a></li>
        <s:authorize access="isAuthenticated()">
            <header:projects />
            <c:if test="${not empty currentUserVoteRequests}">
                <header:voteRequests />
            </c:if>
        </s:authorize>
      </ul>
      <header:authentication />
    </div>
  </div>
</nav>
