<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty breadcrumbs}">
    <ol class="breadcrumb">
        <c:forEach var="breadcrumb" items="${breadcrumbs}" varStatus="loopStatus">
          <c:choose>
            <c:when test="${loopStatus.last}">
               <li><a class="active" href="#">${breadcrumb.title}</a></li>
            </c:when>
            <c:otherwise>
               <li><a href="${ctx}/${breadcrumb.url}">${breadcrumb.title}</a></li>
            </c:otherwise>
          </c:choose>
        </c:forEach>
    </ol>
</c:if>
