package com.hnyp.ahp.web.controllers;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hnyp.ahp.core.data.AlternativeData;
import com.hnyp.ahp.core.exception.AlternativeAlreadyExistException;
import com.hnyp.ahp.core.facades.AlternativeFacade;
import com.hnyp.ahp.core.facades.ProjectDecisionFacade;
import com.hnyp.ahp.web.forms.AlternativeForm;
import com.hnyp.ahp.web.response.AjaxResponseWraper;

//@RestController
//@RequestMapping("/project/{projectId}/decision/{id}")
public class ProjectDecisionRestController {

    @Resource
    private ProjectDecisionFacade projectDecisionFacade;
    @Resource
    private AlternativeFacade alternativeFacade;
    

    
}
