<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<common:pageTemplate title="Register">

    <div class="page-header">
        <h2>Registration</h2>
    </div>
    
    <div class="row">
        
        <div class="col-sm-8">
        
        <form:form cssClass="form-horizontal" action="${ctx}/register" method="post" modelAttribute="registerForm">
            <div class="form-group">
                <label for="inputFirstName"
                    class="col-sm-3 control-label">First Name</label>
                <div class="col-sm-9">
                    <form:input path="firstName" cssClass="form-control" id="inputFirstName" placeholder="your first name" />
                    <form:errors path="firstName" cssClass="error text-danger" element="div" />
                </div>
            </div>
            <div class="form-group">
                <label for="inputLastName"
                    class="col-sm-3 control-label">Last Name</label>
                <div class="col-sm-9">
                    <form:input path="lastName" cssClass="form-control" id="inputLastName" placeholder="your last name" />
                    <form:errors path="lastName" cssClass="error text-danger" element="div" />
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail"
                    class="col-sm-3 control-label">Email</label>
                <div class="col-sm-9">   
                    <form:input path="email" cssClass="form-control" id="inputEmail" placeholder="your email" />
                    <form:errors path="email" cssClass="error text-danger" element="div" />
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword"
                    class="col-sm-3 control-label">Password</label>
                <div class="col-sm-9">
                    <form:password path="password" cssClass="form-control" id="inputPassword" placeholder="your password" />
                    <form:errors path="password" cssClass="error text-danger" element="div" />
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-9">
                    <button type="submit" class="btn btn-default">Register</button>
                </div>
            </div>
        </form:form>
        
        </div>
    </div>


</common:pageTemplate>