<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="tab-criterias" class="tab-pane fade">
    <h3>Criterias</h3>
    
    <p class="text-info"><span class="glyphicon glyphicon-info-sign"></span>
        Criteria are a way of evaluating alternatives among themselves. You will need to determine the relationship between the alternatives based on the criteria you have created. 
    </p>
    
    <br/>
    
    <c:choose>
        <c:when test="${not empty projectDecisionData.criterias}">
            <p class="js-criteria-list-caption">List of criterias</p>
            <ul class="list-group js-criteria-list">
            <c:forEach var="criteria" items="${projectDecisionData.criterias}">
                <li class="list-group-item clearfix js-criteria"
                    data-id="${criteria.id}"
                    data-name="${criteria.name}"
                    data-description="${criteria.description}">
                    
                    <div class="col-xs-10 no-padding">
                        <h4 class="list-group-item-heading js-criteria-name">${ criteria.name }</h4>
                        <p class="list-group-item-text js-criteria-description">
                            ${criteria.description}
                        </p>
                    </div>
                    <div class="col-xs-2 no-padding">
                        <span class="pull-right">
                            <button class="btn btn-xs btn-default js-edit-criteria" title="edit criteria">
                              <span class="glyphicon glyphicon-pencil"></span>
                            </button>
                            <button class="btn btn-xs btn-danger js-remove-criteria" title="remove criteria">
                              <span class="glyphicon glyphicon-remove"></span>
                            </button>
                        </span>
                    </div>
                </li>
            </c:forEach>
            </ul>
        </c:when>
        <c:otherwise>
            <p class="js-criteria-list-caption">Please create criterias you compare alternatives by</p>
            <ul class="list-group js-criteria-list hide"></ul>
        </c:otherwise>
    </c:choose>

    <p>
        <button class="btn btn-default js-add-criteria">
          <span class="glyphicon glyphicon-plus"></span> Add criteria
        </button>
    </p>
    
    <br /> 
    
    <p class="text-info"><span class="glyphicon glyphicon-info-sign"></span>
        Also, you will be able to make a comparison of the criteria among themselves, this will help determine the importance of a particular criterion when making a choice.
    </p>
    
</div>