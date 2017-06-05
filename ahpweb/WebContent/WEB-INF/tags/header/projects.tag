<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<li class="dropdown">
    <a href="#" class="dropdown-toggle"
        data-toggle="dropdown" role="button" aria-haspopup="true"
        aria-expanded="false"> My Projects <span class="caret"></span>
    </a>
    <ul class="dropdown-menu">
        <c:forEach var="project" items="${currentUserProjects}">
            <li><a href="${ctx}/project/${project.id}/details">${project.name}</a></li>
        </c:forEach>
        <li role="separator" class="divider"></li>
        <li><a href="${ctx}/projects">All projects</a></li>
        <li role="separator" class="divider"></li>
        <li><a href="${ctx}/projects/create">Create project</a></li>
    </ul>
</li>
