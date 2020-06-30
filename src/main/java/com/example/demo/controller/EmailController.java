package com.example.demo.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.EmailService;

@Controller
public class EmailController {
@Autowired
private EmailService eservice;

@RequestMapping("/feedback")
@ResponseBody
public String sendEmail(@RequestParam String fd,Principal user) {
//	System.out.println(user.getName());
//	System.out.println(request.isUserInRole("USER"));

Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
System.out.println(principal.toString());
SecurityContext context = SecurityContextHolder.getContext();
Authentication authentication = context.getAuthentication();
Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
boolean authorized = authorities.contains(new SimpleGrantedAuthority("ADMIN"));
System.out.println("admin AUTHORITY "+authorized);
//authorized = authorities.contains(new SimpleGrantedAuthority("USER"));
//System.out.println("USER AUTHORITY"+authorized);
	eservice.sendEmail(user,fd, authorized);
	return "Tested";
}

}
