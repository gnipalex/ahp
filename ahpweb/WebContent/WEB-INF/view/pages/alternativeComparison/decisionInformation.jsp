<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compareAlternatives" tagdir="/WEB-INF/tags/compareAlternatives" %>

<common:pageTemplate title="Decision Information">

    <div class="page-header">
        <h2>Step 1. Decision information
            <span class="pull-right">
            <button class="btn btn-primary" id="decision-info-next-step-btn">Next Step <span class="glyphicon glyphicon-chevron-right"></span></button>
            </span>
        </h2>
    </div>
    
    <div class="row">
        <div class="col-sm-8">
            <p>You've been requested to provide your understanding in project decision</p>
            <p>Please find more details about decision below:</p>
            <compareAlternatives:information />
        </div>
    </div>
    
</common:pageTemplate>