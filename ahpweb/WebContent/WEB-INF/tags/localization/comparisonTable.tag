<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script>
window.app = window.app || {};
window.app.localization = window.app.localization || {};
window.app.localization.comparisonTable = {
    scale : {
        EXTREME_FAVORS : '<spring:message code="comparison.scale.EXTREME_FAVORS" />',
        VERY_STRONGLY_FAVORS : '<spring:message code="comparison.scale.VERY_STRONGLY_FAVORS" />',
        STRONGLY_FAVORS : '<spring:message code="comparison.scale.STRONGLY_FAVORS" />',
        SLIGHTLY_FAVORS : '<spring:message code="comparison.scale.SLIGHTLY_FAVORS" />',
        EQUAL : '<spring:message code="comparison.scale.EQUAL"/>',
        SLIGHTLY_CONCEDE : '<spring:message code="comparison.scale.SLIGHTLY_CONCEDE" />',
        STRONGLY_CONCEDE : '<spring:message code="comparison.scale.STRONGLY_CONCEDE" />',
        VERY_STRONGLY_CONCEDE : '<spring:message code="comparison.scale.VERY_STRONGLY_CONCEDE" />',
        EXTREME_CONCEDE : '<spring:message code="comparison.scale.EXTREME_CONCEDE" />',
    }
};

</script>