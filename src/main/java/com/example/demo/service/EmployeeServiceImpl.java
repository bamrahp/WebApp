package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Employee;
import com.example.demo.model.Role;
import com.example.demo.repo.EmployeeRepository;
//import com.example.demo.repo.RoleRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository userRepository;
   // private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Employee findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(Employee user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = new Role(user,"USER");
        user.setRoles(Collections.singletonList(userRole));
        System.out.println(user+" USer");
        System.out.println(user.getRoles());
        userRepository.save(user);
    }

	@Override
	public void save(Employee emp) {
		// TODO Auto-generated method stub
		userRepository.save(emp);
		
	}

	
//	public Employee findUserByEmpID(int i) {
//		// TODO Auto-generated method stub
//		return userRepository.findOne(i);
//	}

	

}