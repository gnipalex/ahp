<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="header" tagdir="/WEB-INF/tags/header" %>

<nav class="navbar navbar-default  navbar-static-top">
  <div class="container">
    <div class="navbar-header">
         <button type="button" class="navbar-toggle collapsed" 
               data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
           <span class="sr-only">Toggle navigation</span>
           <span class="icon-bar"></span>
           <span class="icon-bar"></span>
           <span class="icon-bar"></span>
         </button>
        <a class="navbar-brand" href="${ctx}" title="Decision Support System - AHP (analytic hierarchy process)">DSS AHP</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse" aria-expanded="false" style="height: 1px;">
      <ul class="nav navbar-nav">
        <li><a href="#about">About</a></li>
        <s:authorize access="isAuthenticated()">
            <header:projects />
            <c:if test="${not empty activeVoteRequests}">
                <header:voteRequests />
            </c:if>
            <c:if test="${not empty activeProjectDecisions}">
                <header:activeProjectDecisions />
            </c:if>
        </s:authorize>
      </ul>
      <header:authentication />
    </div>
  </div>
</nav>
