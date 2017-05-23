<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags" %>

<%@ attribute name="title" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title> 

<link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="${ctx}/static/css/main.css" />

</head>

<body>
    <common:header />
    <div class="container m-lr-20-">
        <jsp:doBody />
        
        <hr>
        
        <footer>
            <p>Footer</p>
        </footer>
        
    </div>
    <script src="${ctx}/static/js/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/main.js"></script>
</body>
