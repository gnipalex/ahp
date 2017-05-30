<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<li class="dropdown">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
        Vote requests <span class="label label-default">${currentUserVoteRequests.size()}</span>
    </a>
    <ul class="dropdown-menu">
        <c:forEach var="voteRequest" items="${currentUserVoteRequests}">
            <li><a href="${ctx}/projectDecision/${voteRequest.projectDecision.Id}">${voteRequest.projectDecision.goal}</a></li>
        </c:forEach>
    </ul>
</li>