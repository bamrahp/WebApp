package com.example.demo.model;

import java.security.Principal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "requests")
public class Request {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
	
	@OneToOne
    private Employee employee;
    
    @Column(name="message")
    private String message;

	public Request() {
		
	}

	public Request(Date orderDate, int employeeID, String message) {
		super();
		this.orderDate = orderDate;
		//this.employeeID = employeeID;
		this.message = message;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
//	public int getEmployeeID() {
//		return employeeID;
//	}
//
//	public void setEmployeeID(int employeeID) {
//		this.employeeID = employeeID;
//	}

	public String getMessage() {
		return message;
	}
	public int getID() {
		return employee.getEmp_id();
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
	
	
}
