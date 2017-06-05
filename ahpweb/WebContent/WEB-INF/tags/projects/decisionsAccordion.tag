<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="id" %>
<%@ attribute name="decisions" type="java.util.Collection" %>
<%@ attribute name="project" type="java.lang.Object" %>

<div class="panel-group" id="${id}">
    <c:forEach var="decision" items="${decisions}">
        <div class="panel panel-default">
          <div class="panel-heading">
                <p class="panel-title">
                  <strong>
                    <a data-toggle="collapse" data-parent="#${id}" href="#${id}-collapse-${decision.id}">${decision.goal}</a>
                  </strong>
                  <span class="pull-right glyphicon glyphicon-chevron-down"></span>
                </p> 
                
          </div>
          <div id="${id}-collapse-${decision.id}" class="panel-collapse collapse">
                <div class="panel-body">
                    <p>${decision.description}</p>
                    <p>Options:<br>
                        <c:forEach var="alternative" items="${decision.alternatives}">
                            <span class="label label-info" title="${alternative.description}">${alternative.name}</span>
                        </c:forEach>
                    </p>
                    <p>Status: ${decision.status}</p>
                    <p>
                        <c:choose>
                            <c:when test="${decision.status eq 'CREATED'}">
                                <a class="pull-right" href="${ctx}/project/${project.id}/decision/${decision.id}/edit">Edit details</a>
                            </c:when>
                            <c:when test="${decision.status eq 'VOTING'}">
                                <a class="pull-right" href="${ctx}/project/${project.id}/decision/${decision.id}">See progress</a>
                            </c:when>
                            <c:otherwise>
                                <a class="pull-right" href="${ctx}/project/${project.id}/decision/${decision.id}">See results</a>
                            </c:otherwise>
                        </c:choose>
                    </p>
                </div>
          </div>
        </div>
    </c:forEach>
</div>
