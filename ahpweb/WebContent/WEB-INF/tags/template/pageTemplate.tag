<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/template" %>

<%@ attribute name="title" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <title>${ not empty title ? title : 'Page Title' }</title>

    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap-theme.min.css" />
    <link rel="stylesheet" href="${ctx}/static/css/main.css" />
</head>
<body>
    <template:navbar />
    <div class="container p-t-2">
        <jsp:doBody />
    </div>
    <script src="${ctx}/static/js/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script src="${ctx}/static/js/main.js"></script>
</body>
</html> 
    
