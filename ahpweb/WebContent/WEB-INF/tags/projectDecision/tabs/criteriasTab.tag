<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="tab-criterias" class="tab-pane fade">
    <h3>Criterias</h3>
    
    <p class="text-info"><span class="glyphicon glyphicon-info-sign"></span>
        Criteria are a way of evaluating alternatives among themselves. You will need to determine the relationship between the alternatives based on the criteria you have created. 
    </p>
    
    <br/>
    
    <p>Please create criterias you compare alternatives by</p>
    
    <c:if test="${not empty projectDecision.criterias}">
        <ul class="list-group">
            <c:forEach var="criteria" items="${projectDecision.criterias}">
                <li class="list-group-item">
                    <span>${criteria.name}</span>
                    <span class="pull-right">
                        <button class="btn btn-xs btn-default js-edit-criteria" title="edit criteria">
                          <span class="glyphicon glyphicon-pencil"></span>
                        </button>
                        <button class="btn btn-xs btn-danger js-remove-criteria" title="remove criteria">
                          <span class="glyphicon glyphicon-remove"></span>
                        </button>
                    </span>
                </li>
            </c:forEach>
        </ul>
    </c:if>
    
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