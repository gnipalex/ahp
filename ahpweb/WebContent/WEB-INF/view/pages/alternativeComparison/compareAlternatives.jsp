<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compareAlternatives" tagdir="/WEB-INF/tags/compareAlternatives" %>
<%@ taglib prefix="localization" tagdir="/WEB-INF/tags/localization" %>

<common:pageTemplate title="Compare Alternatives" pageName="AlternativeComparisonPage">

    <div class="hide" id="project-decision-data-element"
        data-project-id="${projectId}"
        data-decision-id="${projectDecisionData.id}"
    ></div>

    <div class="page-header">
        <h2>Step 2. Compare alternatives
            <span class="pull-right">
            <button class="btn btn-primary" id="decision-info-next-step-btn">Finish <span class="glyphicon glyphicon-chevron-right"></span></button>
            </span>
        </h2>
    </div>
    
    <div class="row">
        <div class="col-xs-12">
            <p>Please provide your comparisons for alternatives</p>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3">
            <ul class="nav nav-pills nav-stacked js-alternative-pills">
              <li class="active"><a data-toggle="pill" href="#info">Information</a></li>
            </ul>
        </div>
        <div class="col-sm-9">
            <div class="tab-content js-alternative-comparison-tabs">
              <div id="info" class="tab-pane fade in active">
                <compareAlternatives:information />
              </div>
            </div>
            
        </div>

    </div>
    
    <localization:comparisonTable />

</common:pageTemplate>