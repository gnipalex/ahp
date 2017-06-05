package com.hnyp.ahp.web.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hnyp.ahp.core.data.AlternativeData;
import com.hnyp.ahp.core.data.CriteriaComparisonTableData;
import com.hnyp.ahp.core.facades.ProjectDecisionFacade;
import com.hnyp.ahp.web.forms.AlternativeForm;
import com.hnyp.ahp.web.response.ResponseWraper;

@RestController
@RequestMapping("/project/{projectId}/decision/{id}")
public class ProjectDecisionRestController {

    @Resource
    private ProjectDecisionFacade projectDecisionFacade;
    
//    @RequestMapping
//    public CriteriaComparisonTableData getCriteriaComparisonTable() {
//        return null;
//    }
    
//    @RequestMapping
//    public ResponseWraper<List<AlternativeData>> createAlternative(@Valid AlternativeForm alternativeForm) {
//        return null;
//    }
//    
//    @RequestMapping
//    public List<AlternativeData> editAlternative(AlternativeForm alternativeForm) {
//        return null;
//    }
    
}
