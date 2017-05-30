<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="tab-voters" class="tab-pane fade">
    <h3>Voters</h3>
    
    <p class="text-info">
        <span class="glyphicon glyphicon-info-sign"></span>
        Voters are experts who will be asked to perform comparison of provided alternatives by criteria respectively. Usually experts have knowledge of a subject.
    </p>
    
    <br />
    
    <p>Please specify list of responsible voters - people who provide their comparisons of alternatives</p>
    
    <c:if test="${not empty projectDecisionData.voteRequests}">
        <ul class="list-group">
            <c:forEach var="voteRequest" items="${projectDecisionData.voteRequests}">
                <li class="list-group-item js-voterequest clearfix" data-id="${voteRequest.id}">
                    <div class="col-sm-10 no-padding">
                        <p><strong>Name:</strong> ${voteRequest.name}</p>
                        <p><strong>Email:</strong> ${voteRequest.email}</p>
                        <p><strong>Comment:</strong> ${voteRequest.comment}</p>
                    </div> 
                    <div class="col-sm-2 no-padding">
                        <div class="pull-right">
                            <button class="btn btn-xs btn-default js-edit-voterequest" title="Edit vote request">
                                <span class="glyphicon glyphicon-pencil"></span>
                            </button>
                            <button class="btn btn-xs btn-danger js-remove-voterequest">
                                <span class="glyphicon glyphicon-remove" title="Remove vote request"></span>
                            </button>
                        </div>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </c:if>
    
    <button class="btn btn-default" id="addVoteRequest">
        <span class="glyphicon glyphicon-plus"></span> Add voter
    </button>
    
</div>
