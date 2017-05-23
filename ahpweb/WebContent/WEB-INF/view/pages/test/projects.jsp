<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<common:pageTemplate title="Your projects">
    <div class="page-header">
        <h3>Your projects</h3>
    </div>
    
    <div class="row">
        <div class="col-sm-6 col-md-3">
            <div class="panel panel-default">
              <div class="panel-heading">Project1</div>
              <div class="panel-body">
                <p>Project description, lorem ipsum ...</p>
                <p>Criterias : criteria1, criteria2, criteria3...</p>
                <p>Alternatives : alternative1, alternative1, alternative1...</p>
                <p><a class="pull-right" href="${ctx}/projects/1">Details</a></p>
              </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-3">
            <div class="panel panel-default">
              <div class="panel-heading">Project1</div>
              <div class="panel-body">
                <p>Project description, lorem ipsum ...</p>
                <p>Criterias : criteria1, criteria2, criteria3...</p>
                <p>Alternatives : alternative1, alternative1, alternative1...</p>
              </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-3">
            <div class="panel panel-default">
              <div class="panel-heading">Project1</div>
              <div class="panel-body">
                <p>Project description, lorem ipsum ...</p>
                <p>Criterias : criteria1, criteria2, criteria3...</p>
                <p>Alternatives : alternative1, alternative1, alternative1...</p>
              </div>
            </div>
        </div>
        <div class="col-sm-6 col-md-3">
            <div class="panel panel-default">
              <div class="panel-heading">Project1</div>
              <div class="panel-body">
                <p>Project description, lorem ipsum ...</p>
                <p>Criterias : criteria1, criteria2, criteria3...</p>
                <p>Alternatives : alternative1, alternative1, alternative1...</p>
              </div>
            </div>
        </div>
    
    </div>
    <c:choose>
        <c:when test="${not empty projects}">
            <c:forEach var="project" items="${projects}">
            
                <!-- project tiles -->
            </c:forEach>
        </c:when>
        <c:otherwise>
            You have no projects yet. <a href="${ctx}/projects/create" class="">Create new project</a>
        </c:otherwise>
    </c:choose>
</common:pageTemplate>
