<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<common:pageTemplate title="Create decision">

    <div class="page-header">
        <h2>Create decision</h2>
    </div>
    
    <div class="row">
        <div class="col-sm-8">
            <form:form cssClass="form-horizontal" action="${ctx}/project/${projectId}/decision/create" method="post" modelAttribute="projectDecisionForm">
                <div class="form-group">
                    <label for="inputGoal"
                        class="col-sm-3 control-label">Goal</label>
                    <div class="col-sm-9">
                        <form:input path="goal" cssClass="form-control" id="inputGoal" placeholder="decision goal" />
                        <form:errors path="goal" cssClass="error text-danger" element="div" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button type="submit" class="btn btn-default">Create</button>
                    </div>
                </div>
            </form:form>
        </div>
    </div>

</common:pageTemplate>