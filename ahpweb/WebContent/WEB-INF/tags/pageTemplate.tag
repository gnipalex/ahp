<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/header" %>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/footer" %>

<%@ attribute name="title" %>
<%@ attribute name="pageName" %>

<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />

<!DOCTYPE html >
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        
        <title>${title}</title> 
        
        <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css" />
        <link rel="stylesheet" href="${ctx}/static/css/bootstrap-theme.min.css" />
        <link rel="stylesheet" href="${ctx}/static/css/main.css" />
        
    </head>

    <body data-page-name="${pageName}">
    
        <header:topNavigation />
        
        <div class="container">
            <header:breadcrumbs />
        
            <header:messages />
        
            <jsp:doBody />
            
            <hr>
            
            <footer:footer />
        </div>
        
        <script>
            window.app = window.app || {};
            window.app.config = window.app.config || {};
            window.app.config.ctx = '${ctx}';
        </script>
        
        <script src="${ctx}/static/js/jquery-3.2.1.min.js"></script>
        <script src="${ctx}/static/js/bootstrap.min.js"></script>
        <script src="${ctx}/static/js/underscore-min.js"></script>
        <script src="${ctx}/static/js/main.js"></script>
    </body>
    
</html>