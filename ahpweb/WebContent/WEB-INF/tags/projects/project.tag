<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="project" type="java.lang.Object" %>

<div class="col-xs-12">
    <div class="panel panel-default">
        <div class="panel-heading">
            <a href="${ctx}/projects/details/${project.id}" >${project.name}</a>
        </div>
        <div class="panel-body">
            <c:if test="${not empty project.description}">
                <p>${project.description}</p>
            </c:if>
            <c:choose>
                <c:when test="${not empty project.projectDecisions}">
                    <p>Decisions : 
                        <c:forEach var="decision" items="${project.projectDecisions}">
                            <span class="glyphicon glyphicon-eye-open"></span> <a title="${decision.description}">${decision.goal}</a>,
                        </c:forEach>
                    </p>
                </c:when>
                <c:otherwise>
                    <p>Project does not contain any decisions yet. 
                        <a href="${ctx}/projects/details/${project.id}" >Open it and add one!</a>
                    </p>
                </c:otherwise>
            </c:choose>
            <p><a href="${ctx}/projects/details/${project.id}" class="pull-right">See details</a></p>
        </div>
    </div>
</div>
