<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>

<common:pageTemplate>

    <div class="page-header">
        <h2>Welcome to DSS AHP</h2>
    </div>
    
    <div class="row">
        <div class="col-sm-8">
        
            <s:authorize access="isAuthenticated()">
            
            <c:if test="${not empty latestDecisions}">
            <h4>Your latest decisions</h4>
            
            <hr>
            </c:if>
            
            <c:if test="${not empty latestDecisions}">
            <h4>Requests to provide your comparisons for alternatives</h4>
            
            <hr>
            </c:if>
            
            </s:authorize>
        
        
        
            <h3>What?</h3>
            <p>DSS AHP is a tool designed to help you with your decisions. 
            As far as decisions can be based on many uncertainties tool can help you to take into account all of them. 
            Create alternatives to select from, create criterias to compare them by. Some criterias can have more influence on decision : just prioterise them. 
            </p>

        </div>
    </div>
    
</common:pageTemplate>

