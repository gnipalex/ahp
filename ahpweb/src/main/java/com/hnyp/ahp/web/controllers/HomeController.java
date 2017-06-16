package com.hnyp.ahp.web.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hnyp.ahp.web.breadcrumb.Breadcrumb;

@Controller
public class HomeController extends AbstractController {
    
    @ModelAttribute("breadcrumbs")
    public List<Breadcrumb> getBreadcrums() {
        return Arrays.asList(
             new Breadcrumb().setTitle("Home Page").setUrl("/")
        );
    }
    
    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String index() {
        return "home";
    }

}
