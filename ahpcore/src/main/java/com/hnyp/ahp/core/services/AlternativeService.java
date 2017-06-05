package com.hnyp.ahp.core.services;

import java.util.List;

import com.hnyp.ahp.core.exception.AlternativeAlreadyExistException;
import com.hnyp.ahp.core.models.Alternative;
import com.hnyp.ahp.core.models.ProjectDecision;

public interface AlternativeService {

    List<Alternative> getForProjectDecision(ProjectDecision projectDecision);

    boolean doesAlternativeExist(ProjectDecision projectDecision, String name);
    
    Alternative getForProjectDecisionAndName(ProjectDecision projectDecision, String name);
    
    /**
     * 
     * @throws AlternativeAlreadyExistException 
     *      in case when alternative with provided project decision and name already exists
     */
    void create(Alternative alternative);
    
    /**
     * 
     * @throws AlternativeAlreadyExistException 
     *      in case when alternative with provided project decision and name already exists
     */
    void update(Alternative alternative);
    
}
