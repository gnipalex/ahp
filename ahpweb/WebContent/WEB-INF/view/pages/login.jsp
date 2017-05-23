<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<common:pageTemplate title="Login">

    <div class="page-header">
        <h2>Please provide your credentials</h2>
    </div>
    <div class="row">
        <div class="col-sm-8">
            <form class="form-horizontal" action="${ctx}/login" method="post">
                <div class="form-group">
                    <label for="inputEmail"
                        class="col-sm-3 control-label">Email</label>
                    <div class="col-sm-9">
                        <input class="form-control" name="login"
                            id="inputEmail" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword"
                        class="col-sm-3 control-label">Password</label>
                    <div class="col-sm-9">
                        <input type="password" class="form-control" name="password"
                            id="inputPassword" placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <div class="checkbox">
                            <label> <input type="checkbox">
                                Remember me
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-3 col-sm-9">
                        <button type="submit" class="btn btn-default">Sign in</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    
</common:pageTemplate>