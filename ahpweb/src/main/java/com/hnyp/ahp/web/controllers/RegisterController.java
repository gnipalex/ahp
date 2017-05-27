package com.hnyp.ahp.web.controllers;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hnyp.ahp.core.data.RegisterData;
import com.hnyp.ahp.core.facades.AccountFacade;
import com.hnyp.ahp.web.forms.RegisterForm;

@Controller
public class RegisterController {

    public static final String FORM_NAME = "registerForm";
    
    @Autowired
    private AccountFacade accountFacade;
    
    @ModelAttribute(FORM_NAME)
    public RegisterForm getForm() {
        return new RegisterForm();
    }
    
    @RequestMapping("/register")
    public String register(Model model) {
        return "register";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String doRegister(@Valid RegisterForm registerForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (StringUtils.isNotBlank(registerForm.getEmail())) {
            if (accountFacade.accountExists(registerForm.getEmail())) {
                bindingResult.rejectValue("email", "Account with provided email already exists");
            }
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(FORM_NAME, registerForm);
            redirectAttributes.addFlashAttribute(ControllerConstants.BINDING_RESULT_PREFIX + FORM_NAME, bindingResult);
            return "redirect:" + "/register";
        }
        
        accountFacade.register(toRegisterData(registerForm));
        redirectAttributes.addAttribute(ControllerConstants.ACCOUNT_REGISTERED, true);
        
        return "redirect:" + "/login";
    }
    
    private RegisterData toRegisterData(RegisterForm form) {
        RegisterData data = new RegisterData();
        data.setEmail(form.getEmail());
        data.setFirstName(form.getFirstName());
        data.setLastName(form.getLastName());
        data.setPassword(form.getPassword());
        return data;
    }
    
}
