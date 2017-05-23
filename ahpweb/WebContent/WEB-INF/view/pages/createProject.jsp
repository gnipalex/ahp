<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<common:pageTemplate title="Create Project">

    <div class="page-header">
        <h2>Create Project</h2>
    </div>
    
    <div class="row">
        <div class="col-sm-8">
            <form:form cssClass="form-horizontal" action="${ctx}/projects/create" method="post" modelAttribute="projectForm">
                <div class="form-group">
                    <label for="inputProjectName"
                        class="col-sm-3 control-label">Name</label>
                    <div class="col-sm-9">
                        <form:input path="name" cssClass="form-control" id="inputProjectName" placeholder="your project name" />
                        <form:errors path="name" cssClass="error text-danger" element="div" />
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputProjectDescription"
                        class="col-sm-3 control-label">Description</label>
                    <div class="col-sm-9">
                        <form:textarea path="description" cssClass="form-control" id="inputProjectDescription" placeholder="your project description" />
                        <form:errors path="description" cssClass="error text-danger" element="div" />
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