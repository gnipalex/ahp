<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<common:pageTemplate title="Project ${project.name} - Details">

    <div class="page-header">
        <h2>Project: ${project.name}</h2>
    </div>
    
    <div class="row">
        <div class="col-xs-12">
        <h4>About:</h4>
        <p>${project.description}</p>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <h4>Decisions:</h4>
            <div class="panel-group" id="decisionsAccordion">
                <c:forEach var="decision" items="${project.projectDecisions}">
                    <div class="panel panel-default">
                      <div class="panel-heading">
                            <h5 class="panel-title">
                              <a data-toggle="collapse" data-parent="#decisionsAccordion" href="#decision-collapse-${decision.id}">${decision.goal}</a>
                            </h5> 
                      </div>
                      <div id="decision-collapse-${decision.id}" class="panel-collapse collapse in">
                            <div class="panel-body">
                                <p>${decision.description}</p>
                                <p>Options:<br>
                                    <c:forEach var="alternative" items="${decision.alternatives}">
                                        <span class="label label-info" title="${alternative.description}">${alternative.name}</span>
                                    </c:forEach>
                                </p>
                                <p>
                                    <a class="pull-right" href="${ctx}/project/${project.id}/decision/${decision.id}">See details</a>
                                </p>
                            </div>
                      </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>


</common:pageTemplate>