package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request,Integer>{

	
	public Optional<Request> findById(int requestId);
}
