<%@tag import="com.hnyp.ahp.lib.ComparisonScale"%>
<%@tag import="java.util.Arrays"%>
<%@tag import="java.util.Map"%>
<%@tag import="java.util.HashMap"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<% 

Map<String, Object> comparisonsData = new HashMap<String, Object>();

comparisonsData.put("criteriaNames", Arrays.asList("criteria1", "criteria2", "criteria3", "criteria4"));
comparisonsData.put("comparisons", Arrays.asList(
    Arrays.asList(
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria1");
                put("alternativeB", "criteria1");
                put("value", ComparisonScale.EQUAL);
            }  
        },
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria1");
                put("alternativeB", "criteria2");
                put("value", ComparisonScale.EQUAL);
            }  
        },
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria1");
                put("alternativeB", "criteria3");
                put("value", ComparisonScale.EQUAL);
            }  
        },
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria1");
                put("alternativeB", "criteria4");
                put("value", ComparisonScale.EQUAL);
            }  
        }
    ),
    Arrays.asList(
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria2");
                put("alternativeB", "criteria1");
                put("value", ComparisonScale.EQUAL);
            }  
        },
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria2");
                put("alternativeB", "criteria2");
                put("value", ComparisonScale.EQUAL);
            }  
        },
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria2");
                put("alternativeB", "criteria3");
                put("value", ComparisonScale.EXTREME_FAVORS);
            }  
        },
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria2");
                put("alternativeB", "criteria4");
                put("value", ComparisonScale.EXTREME_FAVORS);
            }  
        }
    ),
    Arrays.asList(
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria3");
                put("alternativeB", "criteria1");
                put("value", ComparisonScale.EQUAL);
            }  
        },
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria3");
                put("alternativeB", "criteria2");
                put("value", ComparisonScale.EXTREME_CONCEDE);
            }  
        },
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria3");
                put("alternativeB", "criteria3");
                put("value", ComparisonScale.EQUAL);
            }  
        },
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria3");
                put("alternativeB", "criteria4");
                put("value", ComparisonScale.EQUAL);
            }  
        }
    ),
    Arrays.asList(
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria4");
                put("alternativeB", "criteria1");
                put("value", ComparisonScale.EQUAL);
            }  
        },
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria4");
                put("alternativeB", "criteria2");
                put("value", ComparisonScale.EXTREME_CONCEDE);
            }  
        },
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria4");
                put("alternativeB", "criteria3");
                put("value", ComparisonScale.EQUAL);
            }  
        },
        new HashMap<String, Object>() {
            {
                put("alternativeA", "criteria4");
                put("alternativeB", "criteria4");
                put("value", ComparisonScale.EQUAL);
            }  
        }
    )
   )
);

jspContext.setAttribute("comparisonsData", comparisonsData);

%>


 <table class="table table-bordered  js-criteria-comparisons-table">
    <caption>User Details</caption>
    <thead>
        <tr>
            <th></th>
            <c:forEach var="criteriaName" items="${comparisonsData.criteriaNames}">
                <th class="text-center">${criteriaName}</th>
            </c:forEach>
        </tr>
    </thead>
    <tbody>
    <c:forEach var="comparisonsRow" items="${comparisonsData.comparisons}" varStatus="varStatus">
        <tr>
            <th class="text-center">${comparisonsData.criteriaNames[varStatus.index]}</th>
            <c:forEach var="comparison" items="${comparisonsRow}" >
                <td class="text-center">
                    <div class="dropdown js-criteria-dropdown">
                        <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown"
                            data-value="${comparison.value}"
                            data-alternative-a="${comparison.alternativeA}"
                            data-alternative-b="${comparison.alternativeB}">
                            <span><spring:message code="comparison.scale.${comparison.value}" /></span>
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                              <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="EXTREME_FAVORS">
                                    <spring:message code="comparison.scale.EXTREME_FAVORS" /></a></li>
                              <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="VERY_STRONGLY_FAVORS">
                                    <spring:message code="comparison.scale.VERY_STRONGLY_FAVORS" /></a></li>
                              <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="STRONGLY_FAVORS">
                                    <spring:message code="comparison.scale.STRONGLY_FAVORS" /></a></li>
                              <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="SLIGHTLY_FAVORS">
                              <spring:message code="comparison.scale.SLIGHTLY_FAVORS" /></a></li>
                             <li role="presentation" class="divider"></li>
                              <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="EQUAL">
                                    <spring:message code="comparison.scale.EQUAL"/></a></li>
                            <li role="presentation" class="divider"></li>
                              <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="SLIGHTLY_CONCEDE">
                              <spring:message code="comparison.scale.SLIGHTLY_CONCEDE" /></a></li>
                              <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="STRONGLY_CONCEDE">
                              <spring:message code="comparison.scale.STRONGLY_CONCEDE" /></a></li>
                              <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="VERY_STRONGLY_CONCEDE">
                              <spring:message code="comparison.scale.VERY_STRONGLY_CONCEDE" /></a></li>
                              <li role="presentation"><a role="menuitem" tabindex="-1" href="#" data-value="EXTREME_CONCEDE">
                              <spring:message code="comparison.scale.EXTREME_CONCEDE" /></a></li>
                        </ul>
                    </div>
                </td>
            </c:forEach>
        </tr>
    </c:forEach>
    </tbody>
</table>