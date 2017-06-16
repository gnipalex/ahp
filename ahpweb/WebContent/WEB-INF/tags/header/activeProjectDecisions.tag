
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
       Active Decisions <span class="label label-default">${activeProjectDecisions.size()}</span>
    </a>
    <ul class="dropdown-menu">
        <c:forEach var="decision" items="${activeProjectDecisions}">
            <li>
                <a href="${ctx}/project/${decision.projectId}/decision/${decision.id}">
                    ${not empty decision.goal ? decision.goal : 'empty goal'}</a>
            </li>
        </c:forEach>
    </ul>
</li>