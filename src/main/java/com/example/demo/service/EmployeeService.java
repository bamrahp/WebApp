package com.example.demo.service;

import javax.validation.Valid;

import com.example.demo.model.Employee;

public interface EmployeeService {


	void saveUser(@Valid Employee user);

	Employee findUserByEmail(String email);

	void save(Employee emp);


}
