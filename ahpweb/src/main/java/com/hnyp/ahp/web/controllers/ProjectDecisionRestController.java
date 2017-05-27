package com.hnyp.ahp.web.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hnyp.ahp.core.data.CriteriaComparisonTableData;
import com.hnyp.ahp.core.facades.ProjectDecisionFacade;

@RestController
public class ProjectDecisionRestController {

    @Resource
    private ProjectDecisionFacade projectDecisionFacade;
    
    @RequestMapping
    public CriteriaComparisonTableData getCriteriaComparisonTable() {
        return null;
    }
    
}
