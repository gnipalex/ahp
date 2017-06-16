<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="projectDecision" tagdir="/WEB-INF/tags/projectDecision" %>
<%@ taglib prefix="compareCriterias" tagdir="/WEB-INF/tags/compareCriterias" %>
<%@ taglib prefix="localization" tagdir="/WEB-INF/tags/localization" %>

<c:set var="title">${projectDecisionData.goal} - Project Decision Details</c:set>

<common:pageTemplate title="${title}" pageName="CriteriaComparisonPage">

    <div class="hide" id="project-decision-data-element"
        data-project-id="${projectId}"
        data-decision-id="${projectDecisionData.id}"
    ></div>

    <div class="page-header">
        <h2>Step 2. Compare criterias
            <span class="pull-right">
            <button class="btn btn-primary" id="compare-criterias-next-step-btn">Next Step <span class="glyphicon glyphicon-chevron-right"></span></button>
            </span>
        </h2>
    </div>
    
    <div class="row">
        <div class="col-xs-12">
            <p>Please provide comparison for criterias. This is done to prioriterize criterias</p>
            
            <div class="panel-group" id="criteria-comparison-accordion">

            </div>

            <button id="js-add-criteria-comparison-btn" class="btn btn-default">Add criteria comparison</button>
        </div>
    </div>
    
    <localization:comparisonTable />

</common:pageTemplate>