package com.hnyp.ahp.web.services;

import java.util.List;

import com.hnyp.ahp.web.models.Criteria;
import com.hnyp.ahp.web.models.Project;

public interface CriteriaService {
    
    Criteria getById(long id);
    
    List<Criteria> getForProject(Project project);
    
    void save(Criteria criteria);

}
