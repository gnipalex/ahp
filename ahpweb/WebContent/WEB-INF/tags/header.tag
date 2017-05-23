<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>

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
      <a class="navbar-brand" href="#">AHP</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse" aria-expanded="false" style="height: 1px;">
      <ul class="nav navbar-nav">
        <li class="active"><a href="${ctx}">Home</a></li>
        <li><a href="#about">About</a></li>
        <li><a href="#contact">Contact</a></li>
        <s:authorize access="isAuthenticated()">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
                My Projects <span class="caret"></span>
              </a>
              <ul class="dropdown-menu">
                <li><a href="#">Project1</a></li>
                <li><a href="#">Project2</a></li>
                <li><a href="#">Project3</a></li>
                <li><a href="#">All projects</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">Create project</a></li>
              </ul>
            </li>
        </s:authorize>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <s:authorize access="isAuthenticated()">
            <li><a href="#">Welcome on site, <s:authentication property="principal.username" /></a></li>
            <li><a href="${ctx}/logout" class="">Sign out</a></li>
        </s:authorize>
        <s:authorize access="isAnonymous()">
            <li><a href="${ctx}/register"><span class="glyphicon glyphicon-user"></span> Register</a></li>
            <li><a href="${ctx}/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        </s:authorize>
        <li></li>
      </ul>
    </div>
  </div>
</nav>
