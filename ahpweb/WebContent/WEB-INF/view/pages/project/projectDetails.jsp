<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="projects" tagdir="/WEB-INF/tags/projects" %>

<common:pageTemplate title="Project ${project.name} - Details" pageName="ProjectDetailsPage">

    <div class="page-header">
        <h2>Project: ${project.name}</h2>
    </div>
    
    <div class="row">
        <div class="col-xs-12">
            <h4>About project</h4>
            <p>${project.description}</p>
            <a class="btn btn-default" href="${ctx}/project/${project.id}/decision/create">Create decision</a>
        </div>
    </div>
    
    <c:if test="${not empty project.activeDecisions}">
    <div class="row">
        <div class="col-xs-12">
            <h4>Active decisions</h4>
            <projects:decisionsAccordion id="activeProjectDecisions" decisions="${project.activeDecisions}"/>
        </div>
    </div>
    </c:if>
    
    <div class="row">
        <div class="col-xs-12">
            <h4>Decision history</h4>
            <c:choose>
                <c:when test="${not empty project.decisions}">
                    <projects:decisionsAccordion id="projectDecisionHistory" decisions="${project.decisions}"/>
                </c:when>
                <c:otherwise>
                    <p>No decisions present yet. Please <a href="${ctx}/project/${project.id}/decision/create">create new decision.</a></p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

</common:pageTemplate>