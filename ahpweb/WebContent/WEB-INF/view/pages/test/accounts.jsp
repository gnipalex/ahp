<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>

<common:pageTemplate title="Accounts">
    <div class="row">
    
        <div class="col-md-6">
            <c:if test="${not empty accountCreated}">
                <c:choose>
                    <c:when test="${ accountCreated }">
                        <div class="alert alert-success" role="alert">
                            Account was created
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-warning" role="alert">
                            Error during creating account
                        </div>
                    </c:otherwise>
                </c:choose>
            </c:if>
            
            <h2>Create new user</h2>
            <c:url var="createAccountUrl" value="/accounts/create" />
            <form class="form-horizontal" action="${createAccountUrl}" method="post">
                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label">Email</label> 
                    <input name="email" id="email" class="form-control"/>
                </div>
                <div class="form-group">
                    <label for="password" class="col-sm-2 control-label">Password</label> 
                    <input name="password" id="password" class="form-control" />
                </div>
                <div class="form-group">
                    <label for="firstName" class="col-sm-2 control-label">First Name</label> 
                    <input name="firstName" id="firstName" class="form-control" />
                </div>
                <div class="form-group">
                    <label for="lastName" class="col-sm-2 control-label">Last Name</label> 
                    <input name="lastName" name="lastName" class="form-control" />
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Submit</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-6">
            <h2>Available users</h2>
            <c:choose>
                <c:when test="${not empty users}">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Email</th>
                                <th>First Name</th>
                                <th>Last Name</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="u" items="${users}">
                            <tr>
                                <td>${u.email}</td>
                                <td>${u.firstName}</td>
                                <td>${u.lastName}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    No users in database
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
        <form class="form-horizontal">
            <div class="form-group">
                <label for="inputEmail3"
                    class="col-sm-2 control-label">Email</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control"
                        id="inputEmail3" placeholder="Email">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3"
                    class="col-sm-2 control-label">Password</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control"
                        id="inputPassword3" placeholder="Password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <div class="checkbox">
                        <label> <input type="checkbox">
                            Remember me
                        </label>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Sign
                        in</button>
                </div>
            </div>
        </form>
        </div>
    </div>
</common:pageTemplate>