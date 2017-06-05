<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="header-messages">
<c:if test="${not empty successMessage}">
    <div class="alert alert-success" role="alert">${successMessage}</div>
</c:if>
<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger" role="alert">${errorMessage}</div>
</c:if>
</div>