package com.hnyp.ahp.web.controllers.test;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hnyp.ahp.core.models.User;

//@Controller
public class AccountController {

    @Autowired
    private SessionFactory sessionFactory;
    
    @RequestMapping(value="/accounts")
    @Transactional(readOnly=true)
    public String accounts(Model model) {
        List<User> users = sessionFactory.getCurrentSession().createQuery("from User").list();
        model.addAttribute("users", users);
        return "accounts";
    }
    
    @RequestMapping(value="/accounts/create", method=RequestMethod.POST)
    @Transactional
    public String createAccount(AccountForm form, RedirectAttributes redirectAttributes) {
        User user = new User();
        user.setEmail(form.getEmail());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setPassword(form.getPassword());
        
        try {
            sessionFactory.getCurrentSession().save(user);
            redirectAttributes.addAttribute("accountCreated", true);
        } catch (HibernateException e) {
            redirectAttributes.addAttribute("accountCreated", false);
        }
        
        
        return "redirect:/accounts";
    }
    
    public static class AccountForm {
        private String email;
        private String firstName;
        private String lastName;
        private String password;
        
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public String getFirstName() {
            return firstName;
        }
        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
        public String getLastName() {
            return lastName;
        }
        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
        public String getPassword() {
            return password;
        }
        public void setPassword(String password) {
            this.password = password;
        }

    }
    
    
}
