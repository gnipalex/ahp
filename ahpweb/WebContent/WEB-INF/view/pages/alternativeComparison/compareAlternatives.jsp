<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compareAlternatives" tagdir="/WEB-INF/tags/compareAlternatives" %>

<common:pageTemplate title="Compare Alternatives">

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
            <ul class="nav nav-pills nav-stacked">
              <li class="active"><a data-toggle="pill" href="#info">Information</a></li>
              <li><a data-toggle="pill" href="#criteria1">Criteria 1</a></li>
              <li><a data-toggle="pill" href="#criteria2" title="Criteria description text">Criteria 2</a></li>
            </ul>
        </div>
        <div class="col-sm-9">
            
            <div class="tab-content">
              <div id="info" class="tab-pane fade in active">
                <compareAlternatives:information />
              </div>
              <div id="criteria1" class="tab-pane fade">
                <h3>Menu 1</h3>
                <p>Some content in menu 1.</p>
              </div>
              <div id="criteria2" class="tab-pane fade">
                <h3>Menu 2</h3>
                <p>Some content in menu 2.</p>
              </div>
            </div>
            
        </div>

    </div>

</common:pageTemplate>