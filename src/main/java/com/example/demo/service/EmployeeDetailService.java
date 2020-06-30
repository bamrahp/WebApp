//package com.example.demo.service;
//
//import com.example.demo.model.Employee;
//import com.example.demo.model.Role;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@Service
//public class EmployeeDetailService implements UserDetailsService {
//
//    @Autowired
//    private EmployeeService userService;
//
//    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Employee emp = userService.findUserByEmail(userName);
//        List<GrantedAuthority> authorities = getUserAuthority(emp.getRoles());
//        return buildUserForAuthentication(emp, authorities);
//    }
//
//    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
//        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
//        for (Role role : userRoles) {
//            roles.add(new SimpleGrantedAuthority(role.getRole()));
//        }
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
//        return grantedAuthorities;
//    }
//
//    private UserDetails buildUserForAuthentication(Employee user, List<GrantedAuthority> authorities) {
//        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//                user.getActive(), true, true, true, authorities);
//    }
//}