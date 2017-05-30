<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="tabs" tagdir="/WEB-INF/tags/projectDecision/tabs" %>

<ul class="nav nav-pills nav-justified">
    <li class="active"><a href="#tab-information" data-toggle="pill">Information</a></li>
    <li><a href="#tab-alternatives" data-toggle="pill">Alternatives</a></li>
    <li><a href="#tab-criterias" data-toggle="pill">Criterias</a></li>
    <li><a href="#tab-voters" data-toggle="pill">Voters</a></li>
</ul>

<div class="tab-content">
    <tabs:basicInformationTab />
    <tabs:alternativesTab />
    <tabs:criteriasTab />
    <tabs:votersTab />
</div>
