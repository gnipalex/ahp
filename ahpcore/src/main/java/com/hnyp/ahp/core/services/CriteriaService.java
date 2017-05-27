package com.hnyp.ahp.core.services;

import java.util.List;

import com.hnyp.ahp.core.models.Criteria;
import com.hnyp.ahp.core.models.Project;

public interface CriteriaService {
    
    Criteria getById(long id);
    
    List<Criteria> getForProject(Project project);
    
    void save(Criteria criteria);

}
