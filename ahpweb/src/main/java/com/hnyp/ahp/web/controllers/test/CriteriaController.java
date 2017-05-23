package com.hnyp.ahp.web.controllers.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hnyp.ahp.web.forms.CreateCriteriaForm;

//@Controller
@RequestMapping(value="/criterias")
public class CriteriaController {

    @RequestMapping(value="/add")
    public String add() {
        return "addCriteria";
    }
    
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public String doAdd(CreateCriteriaForm form) {
        return "redirect:" + form.getRedirectUrl();
    }
    
    @RequestMapping(value="/{version}/compare")
    public String compareCriterias(String version) {
        return "compareCriterias";
    }
    
}
