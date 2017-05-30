<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="projectDecision" tagdir="/WEB-INF/tags/projectDecision" %>

<c:set var="title">${projectDecisionData.goal} - Project Decision Details</c:set>

<common:pageTemplate title="${title}">

    <div class="page-header">
        <h2>Step 1. Edit decision.</h2>
    </div>
    
    <div class="row">
        <div class="col-xs-12">
            <projectDecision:tabs />
        </div>
    </div>
    
    <hr>
    
    <p class="text-warning">
        <span class="glyphicon glyphicon-warning-sign"></span>
        Please take into account that 
        information about project decision can not be 
        edited when comparison of alternatives is started.
    </p>

</common:pageTemplate>