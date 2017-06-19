<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="projects" tagdir="/WEB-INF/tags/projects" %>
<common:pageTemplate title="Projects">

    <div class="page-header">
        <h2>Projects dashboard</h2>
    </div>
    
    <div class="row">
        <div class="col-xs-12">
            <p>Here is displayed list of all your projects</p>
        </div>
        <c:if test="${ not empty projects}">
            <c:forEach var="project" items="${projects}">
                <projects:project project="${project}"/>
            </c:forEach>
        </c:if>
        <div class="col-xs-12">
            <a href="${ctx}/projects/create" class="btn btn-default">Create project</a>
        </div>
    </div>

</common:pageTemplate>