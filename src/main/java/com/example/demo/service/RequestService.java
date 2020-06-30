package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Request;
import com.example.demo.repo.RequestRepository;

@Service
@Transactional
public class RequestService {
	 private final EntityManager entityManager;
	
	private RequestRepository requestRepo;
	
	@Autowired
	public RequestService(EntityManager entityManager, RequestRepository requestRepo) {
		super();
		this.entityManager = entityManager;
		this.requestRepo = requestRepo;
	}

	public void save(Request req) {
		requestRepo.save(req);
	}
	
	public List<Request> findAll() {
		return requestRepo.findAll();
	}

	public Request findById(int id) {
		// TODO Auto-generated method stub
		return requestRepo.findById(id).get();
	}


	public void remove(Optional<Request> req) {
		// TODO Auto-generated method stub
		System.out.println("*************");
		entityManager.remove(req);
		System.out.println("_________________");
		
	}

	public void delete(Request req) {
		// TODO Auto-generated method stub
		requestRepo.delete(req);
	}


}
