package com.example.demo.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@Controller
public class LoginController {

    @Autowired
    private EmployeeService empService;
    @PreAuthorize("isAuthenticated()")
    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
    	System.out.println("Inside registration");
        ModelAndView modelAndView = new ModelAndView();
        Employee emp= new Employee();
        modelAndView.addObject("user", emp);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Employee user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Employee userExists = empService.findUserByEmail(user.getEmail());
//        Employee IdExists = empService.findUserByEmpID(user.getEmp_id());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the user name provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
        	
            empService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new Employee());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

   


}