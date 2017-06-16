<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty projectData}">
    <h3>Project description</h3>
    <p><strong>Name:</strong> ${projectData.name}</p>
    <p><strong>Description:</strong> ${projectData.description}</p>
</c:if>

<h3>Decision description</h3>
<p><strong>Goal:</strong> ${projectDecisionData.goal}</p>
<p><strong>Description:</strong> ${projectDecisionData.description}</p>

<h3>Alternatives</h3>
<ul>
<c:forEach var="alternative" items="${projectDecisionData.alternatives}">
    <li>${alternative.name} - ${alternative.description}</li>
</c:forEach>
</ul>

<h3>Criteria description</h3>
<ul>
<c:forEach var="criteria" items="${projectDecisionData.criterias}">
    <li>${criteria.name} - ${criteria.description}</li>
</c:forEach>
</ul>