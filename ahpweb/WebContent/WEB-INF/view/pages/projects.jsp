<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                <div class="col-xs-12 col-sm-6 col-md-4">
                    <div class="panel panel-primary">
                        <div class="panel-heading">${project.name}</div>
                        <div class="panel-body">
                            <p>${project.description}</p>
                            <p><a href="${ctx}/projects/details/${project.id}" class="pull-right">See details</a></p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <div class="col-xs-12">
            <a href="${ctx}/projects/create" class="btn btn-default">Create project</a>
        </div>
    </div>

</common:pageTemplate>