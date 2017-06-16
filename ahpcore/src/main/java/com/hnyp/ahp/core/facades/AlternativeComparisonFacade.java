package com.hnyp.ahp.core.facades;

import java.util.List;

import com.hnyp.ahp.core.data.AlternativeComparisonTableData;
import com.hnyp.ahp.core.data.ComparisonPairData;

public interface AlternativeComparisonFacade {

    void create(long decisionId);
    
    boolean doesComparisonExistForProjectDecision(long projectDecisionId, long id);
    
    AlternativeComparisonTableData getForDecisionAndId(long projectDecisionId, long id);
    
    List<AlternativeComparisonTableData> getAlternativeComparisonsForDecision(long decisionId);
    
    void update(long comparisonTableId, List<ComparisonPairData> comparisonPairs);
    
}
