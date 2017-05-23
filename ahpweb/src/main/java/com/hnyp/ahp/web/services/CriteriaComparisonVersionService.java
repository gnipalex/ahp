package com.hnyp.ahp.web.services;

import com.hnyp.ahp.web.models.CriteriaComparisonVersion;
import com.hnyp.ahp.web.models.Project;

public interface CriteriaComparisonVersionService {

    CriteriaComparisonVersion getVersion(Project project, long id);
    
}
