<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="projectDecision" tagdir="/WEB-INF/tags/projectDecision" %>

<c:set var="title">${projectDecisionData.goal} - Project Decision Details</c:set>

<common:pageTemplate title="${title}" pageName="CompareProjectDecisionCriteriasPage">

    <div class="page-header">
        <h2>Step 2. Compare criterias</h2>
    </div>
    
    <div class="row">
        <div class="col-xs-12">
        
            <p>You can provide comparison for criterias. This is done to prioriterize criterias</p>
            
            <div class="radio">
              <label><input type="radio" name="optradio">Prioterisation is not necessary</label>
            </div>
            <div class="radio">
              <label><input type="radio" name="optradio" checked="checked">Prioriterize criterias</label>
            </div>
        
            <button class="btn btn-default">S</button>
        
        </div>
    </div>

</common:pageTemplate>