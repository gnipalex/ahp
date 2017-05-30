package com.hnyp.ahp.web.controllers.test;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hnyp.ahp.core.data.CriteriaComparisonsData_old;
import com.hnyp.ahp.core.models.CriteriaComparison;
import com.hnyp.ahp.core.models.CriteriaComparisonVersion;
import com.hnyp.ahp.core.services.CriteriaComparisonService;
import com.hnyp.ahp.core.services.CriteriaComparisonVersionService;
import com.hnyp.ahp.core.services.CriteriaService;
import com.hnyp.ahp.core.services.ProjectService;
import com.hnyp.ahp.lib.Comparison;
import com.hnyp.ahp.lib.ComparisonMatrix;
import com.hnyp.ahp.lib.PriorityMatrix;
import com.hnyp.ahp.web.v2.models.Criteria;
import com.hnyp.ahp.web.v2.models.Project;

//@Controller
@RequestMapping(value="/projects")
public class ProjectsController {

    @Resource
    private CriteriaComparisonService criteriaComparisonService;
    @Resource
    private CriteriaComparisonVersionService criteriaComparisonVersionService;
    @Resource
    private ProjectService projectService;
    @Resource
    private CriteriaService criteriaService;

    @RequestMapping
    public String projects() {
        return "projects";
    }
    
    @RequestMapping(value="/{projectId}")
    public String projectDetails(@PathVariable long projectId) {
        return "projectDetails";
    }
    
    @RequestMapping(value="/{projectId}/criterias/{versionId}")
    public String criterias(@PathVariable long projectId, @PathVariable long versionId, Model model) {
//        Project project = projectService.getProject(projectId);
//        CriteriaComparisonVersion version = criteriaComparisonVersionService.getVersion(project, versionId);
//        
//        List<Criteria> criteriasForProject = criteriaService.getForProject(project);
//        
//        ComparisonMatrix comparisonMatrix = createComparisonMatrix(criteriasForProject);
//        
//        List<CriteriaComparison> criteriaComparisons = criteriaComparisonService.getCriteriaComparisons(project, version); 
//        if (CollectionUtils.isNotEmpty(criteriaComparisons)) {
//            criteriaComparisons.forEach(it -> {
//                Comparison comparison = criteriaComparisonService.getComparison(it);
//                comparisonMatrix.update(comparison);
//            });
//        }
//
//        CriteriaComparisonsData data = new CriteriaComparisonsData();
//        data.setComparisons(comparisonMatrix.getComparisons());
//        data.setComparisonsVersion(version.getName());
//        data.setComparisonVersionId(versionId);
//        data.setCriteriaNames(criteriasForProject.stream().map(Criteria::getName).collect(Collectors.toList()));
//        data.setProjectId(projectId);
//        
//        PriorityMatrix priorityMatrix = comparisonMatrix.createPriorityMatrix();
//        data.setComputedPriority(priorityMatrix.getPriorities());
//        data.setComputedConsistencyRatio(priorityMatrix.getConsistencyRatio());
//        
//        model.addAttribute("comparisonsData", data);
//        
        return "compareCriterias";
    }
    
    private ComparisonMatrix createComparisonMatrix(List<Criteria> criteriasForProject) {
        Set<String> criteriaIds = criteriasForProject.stream().map(Criteria::getId).map(String::valueOf).collect(Collectors.toSet());
        ComparisonMatrix matrix = new ComparisonMatrix(criteriaIds);
        return matrix;
    }
    
    @RequestMapping(value = "/{projectId}/criterias/{versionId}", method = RequestMethod.POST)
    public String saveComparisons(@PathVariable long projectId, @PathVariable long versionId) {
        
        
        
        return "redirect:/" + projectId + "/criterias/" + versionId;
                
    }
    
    
    
}
