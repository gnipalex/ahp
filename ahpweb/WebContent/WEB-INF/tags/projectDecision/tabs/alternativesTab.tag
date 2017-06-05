<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="tab-alternatives" class="tab-pane fade">
    <h3>Alternatives</h3>
    
    <p class="text-info">
        <span class="glyphicon glyphicon-info-sign"></span>
        Alternative is the way to achieve the goal of the decision. 
        They will be compared against each other based on some criterias. 
        Normally you should select more than two alternatives.
    </p>
    
    <br />
    
    <c:choose>
        <c:when test="${not empty projectDecisionData.alternatives}">
            <p>List of alternatives</p>
            <ul class="list-group js-alternative-list">
                <c:forEach var="alternative" items="${projectDecisionData.alternatives}">
                    <li class="list-group-item clearfix js-alternative" 
                            data-id="${alternative.id}"
                            data-name="${alternative.name}"
                            data-description="${alternative.description}"> 
                        <div class="col-sm-10 no-padding">
                            <h4 class="list-group-item-heading js-alternative-name">${alternative.name}</h4>
                            <p class="list-group-item-text js-alternative-description">${alternative.description}
                                
                            </p>
                        </div>
                        <div class="col-sm-2 no-padding">
                            <span class="pull-right">
                                <button class="btn btn-xs btn-default js-edit-alternative" title="Edit alternative">
                                    <span class="glyphicon glyphicon-pencil"></span>
                                </button>
                                <button class="btn btn-xs btn-danger js-remove-alternative">
                                    <span class="glyphicon glyphicon-remove" title="Remove alternative"></span>
                                </button>
                            </span>
                        </div>
                    </li>
                </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p>List of alternatives is empty. Please add some alternative to perform decision from.</p>
        </c:otherwise>
    </c:choose>
    
    <button class="btn btn-default js-add-alternative">
        <span class="glyphicon glyphicon-plus"></span> Add alternative
    </button>
    
</div>
