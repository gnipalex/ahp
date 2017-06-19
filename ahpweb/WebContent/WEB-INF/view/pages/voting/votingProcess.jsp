<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.hnyp.ahp.core.models.VoteRequestStatus"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compareAlternatives" tagdir="/WEB-INF/tags/compareAlternatives" %>
<%@ taglib prefix="localization" tagdir="/WEB-INF/tags/localization" %>

<%

List<Map> requests = new ArrayList();

requests.add(new HashMap<String, Object>() {
    {
        put("name", "Oleksandr Hnyp");
        put("email", "alex.hnyp@gmail.com");
        put("status", VoteRequestStatus.CONFIRMED);
    }
});

requests.add(new HashMap<String, Object>() {
    {
        put("name", "Test user");
        put("email", "testuser@email.com");
        put("status", VoteRequestStatus.SENT);
    }
});

requests.add(new HashMap<String, Object>() {
    {
        put("name", "Nick");
        put("email", "nick@email.com");
        put("status", VoteRequestStatus.DENIED);
    }
});

pageContext.setAttribute("voteRequests", requests);

%>




<common:pageTemplate title="Voting Process" pageName="AlternativeComparisonPage">

    <div class="hide" id="project-decision-data-element"
        data-project-id="${projectId}"
        data-decision-id="${projectDecisionData.id}"
    ></div>

    <div class="page-header">
        <h2>Step 3. Voting Process
            <span class="pull-right">
            <button class="btn btn-primary" id="decision-info-next-step-btn">Finish <span class="glyphicon glyphicon-chevron-right"></span></button>
            </span>
        </h2>
    </div>
    
    <div class="row">
        <div class="col-xs-12">
            <p>Experts were notified and voting is in progress</p>
            <h4>Requests statuses</h4>
            <div class="row">
                <c:forEach var="voteRequest" items="${voteRequests}">
                    <c:if test="${voteRequest.status == 'CONFIRMED'}">
                        <c:set var="panelClass" value="panel-primary" />
                    </c:if>
                    <c:if test="${voteRequest.status == 'DENIED'}">
                        <c:set var="panelClass" value="panel-danger" />
                    </c:if>
                    <c:if test="${voteRequest.status == 'DENIED'}">
                        <c:set var="panelClass" value="panel-danger" />
                    </c:if>
                    <div class="col-xs-4 panel panel-default">
                      <div class="panel-body">
                        Basic panel example
                      </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    
</common:pageTemplate>