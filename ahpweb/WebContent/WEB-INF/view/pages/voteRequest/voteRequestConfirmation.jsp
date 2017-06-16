<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="compareAlternatives" tagdir="/WEB-INF/tags/compareAlternatives" %>

<common:pageTemplate title="Decision Information" pageName="VoteRequestConfirmationPage">

    <div class="page-header">
        <h2>Step 1. Confirm Vote Request
            <span class="pull-right">
            <button class="btn btn-primary" id="confirm-vote-request-next-step-btn">Next Step <span class="glyphicon glyphicon-chevron-right"></span></button>
            </span>
        </h2>
    </div>
    
    <div class="row">
        <div class="col-sm-8">
            <p>You've been requested to provide your understanding in project decision</p>
            <p>Comment from decision owner:</p>
            <blockquote>
                <p>${voteRequestData.comment}</p>
            </blockquote>
            
            <p>Please find more details about decision below:</p>
            <compareAlternatives:information />
            
            <p>
                <form action="${ctx}/" method="post">
                    <button class="btn btn-primary">Accept and proceed</button>
                </form>
                <form action="" method="post">
                    <button class="btn btn-default">Deny</button>
                </form>
            </p>
            
        </div>
    </div>
    
</common:pageTemplate>