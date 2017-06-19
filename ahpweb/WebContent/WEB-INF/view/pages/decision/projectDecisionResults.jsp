<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compareAlternatives" tagdir="/WEB-INF/tags/compareAlternatives" %>


<common:pageTemplate title="Decision Results" pageName="AlternativeComparisonPage">

    <div class="hide" id="project-decision-data-element"
        data-project-id="${projectId}"
        data-decision-id="${projectDecisionData.id}"
    ></div>

    <div class="page-header">
        <h2>Step 4. Decision Results
            <span class="pull-right">
            <button class="btn btn-primary" id="decision-info-next-step-btn">Finish <span class="glyphicon glyphicon-chevron-right"></span></button>
            </span>
        </h2>
    </div>
    
        <div class="row">
            <div class="col-sm-12">
                <h3>Decision information</h3>
                <p><strong>Goal:</strong> Add new feature</p>
                <p><strong>Description:</strong> We want to make our phone better. 
                There are several options available that we prepared for now. 
                We want to be sure that we select the most apropriate option at the current step of our business. </p>
                <!-- <h3>Criteria prioritization:</h3> -->
            </div>
        </div>
        
        <div class="row">
            <div class="col-sm-12">
                <h3>Criteria priorities</h3>
            </div>
            <div class="col-sm-12">
                <div class="panel panel-success js-decision-result-panel">
                  <div class="panel-heading">
                    <h3 class="panel-title">For Ukranian Market</h3>
                  </div>
                  <div class="panel-body">
                      <div class="js-decision-chart"      
                        data-chart-data='[
                              ["Criteria", "Rank"],
                              ["Price", 0.2582],
                              ["Apps Market", 0.105],
                              ["Necessity (Must Have)", 0.64]
                            ]'
                      ></div>
                      <!-- <dl>
                        <dt>Alternative A</dt>
                        <dd>63%</dd>
                        <dt>Alternative C</dt>
                        <dd>25%</dd>
                        <dt>Alternative B</dt>
                        <dd>12%</dd>
                      </dl> -->
                  </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="col-sm-12">
                <h3>Group decision result</h3>
            </div>
            <div class="col-sm-12">
                <div class="panel panel-success js-decision-result-panel">
                  <div class="panel-heading">
                    <h3 class="panel-title">Group Decision Results</h3>
                  </div>
                  <div class="panel-body">
                      <div class="js-decision-chart"      
                            data-chart-data='[
                              ["Alternative", "Oleksandr", "Nick"],
                              ["Screen", 0.24, 0.24],
                              ["Weight", 0.03, 0.03],
                              ["Battery Life", 0.13, 0.17],
                              ["4G Support", 0.04, 0.16],
                              ["Performance", 0.55, 0.42]
                            ]'
                      ></div>
                      <!-- <dl>
                        <dt>Alternative A</dt>
                        <dd>63%</dd>
                        <dt>Alternative C</dt>
                        <dd>25%</dd>
                        <dt>Alternative B</dt>
                        <dd>12%</dd>
                      </dl> -->
                  </div>
                </div>
                
            </div>
        </div>
        
        <script>
        document.addEventListener("DOMContentLoaded", function(){ 
            google.charts.load("current", {packages:["corechart", "bar"]});
        	
            google.charts.setOnLoadCallback(function() {
        	    $('.js-decision-result-panel').each(drawDecisionResult);
        	});
        	
        	
        	function drawDecisionResult($decisionPanel) {
        	    var $decisionPanel = $(this);
        	    var $chartElement = $decisionPanel.find('.js-decision-chart');
        	    var chartData = $chartElement.data('chart-data');
        	    
        	    if (_.isEmpty(chartData)) {
        	        return;
        	    }
        	    
        	    var data = google.visualization.arrayToDataTable(chartData);

    	        var options = {
    	          chart: {
    	            title: 'Decision results',
    	            subtitle: ''
    	          },
    	          isStacked: true
    	        };

    	        var chart = new google.charts.Bar($chartElement[0]);

    	        chart.draw(data, google.charts.Bar.convertOptions(options));
        	    
        	}
        	
        	function toChartDataTable(values) {
        	    var chartData = [];
        	    var chartAxis = [];
        	    _.each(values, function(v) {
        	        chartData.push();
        	    });
        	    return google.visualization.arrayToDataTable(chartData);
        	}
        	
        });
        
        	
        	
        	
        	
        </script>
        
        <div class="row">
            <div class="col-sm-12">
                <h3>Expert results</h3>
            </div>
            <div class="col-sm-6">
                <div class="panel panel-success js-decision-result-panel">
                  <div class="panel-heading">
                    <h3 class="panel-title">Oleksandr Hnyp</h3>
                  </div>
                  <div class="panel-body">
                      <div class="js-decision-chart"
                        data-chart-data='[
                              ["Alternative", "Rank"],
                              ["Screen", 0.24],
                              ["Weight", 0.03],
                              ["Battery Life", 0.13],
                              ["4G Support", 0.04],
                              ["Performance", 0.55]
                            ]'
                      ></div>
                      <!-- <dl>
                        <dt>Bigger screen</dt>
                        <dd>41%</dd>
                        <dt>Less weight</dt>
                        <dd>20%</dd>
                        <dt>Increase battery</dt>
                        <dd>34%</dd>
                        <dt>Add 4G support</dt>
                        <dd>5%</dd>
                      </dl> -->
                  </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="panel panel-success js-decision-result-panel">
                  <div class="panel-heading">
                    <h3 class="panel-title">Nick</h3>
                  </div>
                  <div class="panel-body">
                      <div class="js-decision-chart"
                        
                        data-chart-data='[
                              ["Alternative", "Rank"],
                              ["Screen", 0.24],
                              ["Weight", 0.03],
                              ["Battery Life", 0.17],
                              ["4G Support", 0.16],
                              ["Performance", 0.42]
                            ]'
                            
                            ></div>
                      <!-- <dl>
                        <dt>Bigger screen</dt>
                        <dd>41%</dd>
                        <dt>Less weight</dt>
                        <dd>20%</dd>
                        <dt>Increase battery</dt>
                        <dd>34%</dd>
                        <dt>Add 4G support</dt>
                        <dd>5%</dd>
                      </dl> -->
                  </div>
                </div>
            </div>
        </div>

</common:pageTemplate>