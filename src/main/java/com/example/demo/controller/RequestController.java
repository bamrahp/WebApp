package com.example.demo.controller;


import java.security.Principal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Employee;
import com.example.demo.model.Request;
import com.example.demo.service.EmployeeService;
import com.example.demo.service.RequestService;

@Controller
public class RequestController {
@Autowired 
private RequestService requestService;
@Autowired EmployeeService empService;
	@GetMapping("/request")
	public String getPage() {
		return "request";
	}
	
	@PostMapping("/request")
	public String postRequest(@RequestParam String message,Principal user) {
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		Employee emp = empService.findUserByEmail(user.getName());
		Request obj = new Request();
		obj.setOrderDate(date);
		obj.setMessage(message);
		obj.setEmployee(emp);
		requestService.save(obj);
		//obj.setEmployeeID(employeeID);
		return "request";
	}
	
	@GetMapping("/admin/requests")
	public ModelAndView getRequests() {
		List<Request> requests= requestService.findAll();
//		for(Request req: requests) {
//			req.getEmployee().getEmp_id()
//		}
		System.out.println(requests.toString());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/requests");
		mv.addObject("requests", requests);
		return mv;
	}
	
	@PostMapping("/admin/request/delete/{id}")
	public ModelAndView markCompleted( @PathVariable int id) {
	
		Request req = requestService.findById(id);
		if(req!=null)
			requestService.delete(req);
		List<Request> requests= requestService.findAll();
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/admin/requests");
		mv.addObject("requests", requests);
		return mv;
	}
}
