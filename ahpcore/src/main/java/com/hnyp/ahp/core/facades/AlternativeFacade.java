package com.hnyp.ahp.core.facades;

import java.util.List;

import com.hnyp.ahp.core.data.AlternativeData;

public interface AlternativeFacade {

    List<AlternativeData> getAlternativesForDecision(long decisionId);
    
    void createAlternative(long projectDecisionId, AlternativeData alternativeData);
    
    void update(AlternativeData alternativeData);
    
    void remove(long alternativeId);
    
    boolean doesAlternativeExistForProjectDecision(long projectDecisionId, String alternativeName);
    
    AlternativeData getAlternativeForDecisionAndName(long projectDecisionId, String alternativeName);
    
}
