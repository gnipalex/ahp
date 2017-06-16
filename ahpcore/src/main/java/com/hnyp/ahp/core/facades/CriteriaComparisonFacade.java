package com.hnyp.ahp.core.facades;

import java.util.List;

import com.hnyp.ahp.core.data.ComparisonPairData;
import com.hnyp.ahp.core.data.CriteriaComparisonTableData;

public interface CriteriaComparisonFacade {

    boolean doesComparisonExistForDecisionAndName(long decisionId, String name);
    
    boolean doesComparisonExistForDecision(long decisionId, long comparisonTableId);
    
    CriteriaComparisonTableData getForDecisionAndName(long decisionId, String name);
    
    CriteriaComparisonTableData getForDecisionAndId(long decisionId, long id);
    
    List<CriteriaComparisonTableData> getForDecision(long decisionId);
    
    void create(long decisionId, CriteriaComparisonTableData criteriaComparisonTableData);
    
    void update(long comparisonId, List<ComparisonPairData> comparisonPairs);
    
    void remove(long id);
    
}
