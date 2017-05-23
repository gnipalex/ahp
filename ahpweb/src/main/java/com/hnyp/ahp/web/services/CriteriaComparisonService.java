package com.hnyp.ahp.web.services;

import java.util.List;

import com.hnyp.ahp.lib.Comparison;
import com.hnyp.ahp.web.models.CriteriaComparison;
import com.hnyp.ahp.web.models.CriteriaComparisonVersion;
import com.hnyp.ahp.web.models.Project;

public interface CriteriaComparisonService {
    
    List<CriteriaComparison> getCriteriaComparisons(Project project, CriteriaComparisonVersion version);
    
    void update(CriteriaComparison comparison);
    
    List<CriteriaComparison> createBlankComparisons(Project project, CriteriaComparisonVersion version);
    
    CriteriaComparison getCriteriaComparison(Comparison comparison, CriteriaComparisonVersion version);
    
    Comparison getComparison(CriteriaComparison criteriaComparison);
}
