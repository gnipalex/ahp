<%@ taglib prefix="common" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<common:pageTemplate title="Compare criterias">

    <ul class="breadcrumb">
          <li><a href="#" contenteditable="true">Home</a> <span class="divider">/</span></li>
          <li><a href="#" contenteditable="true">Project</a> <span class="divider">/</span></li>
          <li><a href="#" contenteditable="true">Criterias</a> <span class="divider">/</span></li>
          <li><a href="#" contenteditable="true">Version</a> <span class="divider">/</span></li>
          <li class="active" contenteditable="true">Compare</li>
    </ul>
    
    <div class="page-header">
        <h3>Compare criterias for ${comparisonsData.comparisonsVersion}</h3>
    </div>
    <div class="row">
        <h3>Pairwise comparisons</h3>
        <table class="table js-criteria-comparisons-table">
            <tr>
                <th></th>
                <c:forEach var="criteriaName" items="${comparisonsData.criteriaNames}">
                    <th>${criteriaName}</th>
                </c:forEach>
            </tr>
            <c:forEach var="comparisonsRow" items="${comparisonsData.comparisons}" varStatus="varStatus">
                <tr>
                    <th>${comparisonsData.criteriaNames[varStatus.index]}</th>
                    <c:forEach var="comparison" items="${comparisonsRow}" >
                        <td>
                            <div class="dropdown js-criteria-dropdown">
                                <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                                    data-value="${comparison.value}"
                                    data-alternative-a="${comparison.alternativeA}"
                                    data-alternative-b="${comparison.alternativeB}">
                                    <span><spring:message code="comparison.scale.${comparison.value}" /></span>
                                    <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="EXTREME_FAVORS">
                                            <spring:message code="comparison.scale.EXTREME_FAVORS" /></a></li>
                                      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="VERY_STRONGLY_FAVORS">
                                            <spring:message code="comparison.scale.VERY_STRONGLY_FAVORS" /></a></li>
                                      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="STRONGLY_FAVORS">
                                            <spring:message code="comparison.scale.STRONGLY_FAVORS" /></a></li>
                                      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="SLIGHTLY_FAVORS">
                                      <spring:message code="comparison.scale.SLIGHTLY_FAVORS" /></a></li>
                                     <li role="presentation" class="divider"></li>
                                      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="EQUAL">
                                            <spring:message code="comparison.scale.EQUAL"/></a></li>
                                    <li role="presentation" class="divider"></li>
                                      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="SLIGHTLY_CONCEDE">
                                      <spring:message code="comparison.scale.SLIGHTLY_CONCEDE" /></a></li>
                                      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="STRONGLY_CONCEDE">
                                      <spring:message code="comparison.scale.STRONGLY_CONCEDE" /></a></li>
                                      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="VERY_STRONGLY_CONCEDE">
                                      <spring:message code="comparison.scale.VERY_STRONGLY_CONCEDE" /></a></li>
                                      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="EXTREME_CONCEDE">
                                      <spring:message code="comparison.scale.EXTREME_CONCEDE" /></a></li>
                                </ul>
                            </div>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
        <!--
        <button class="btn btn-default js-update-criteria-comparisons">Update</button>
        -->
    </div>
    <div class="row">
        <p>Computed priorities : <br>
            <c:forEach var="criteriaName" items="${comparisonsData.criteriaNames}" varStatus="varStatus">
                ${criteriaName} - ${comparisonsData.computedPriority[varStatus.index]}<br>
            </c:forEach>
        </p>
        <p>
        Consistency ratio : ${comparisonsData.computedConsistencyRatio}
        </p>
    </div>
    
</common:pageTemplate>