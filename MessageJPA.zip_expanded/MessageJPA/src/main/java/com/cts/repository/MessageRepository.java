package com.cts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.model.Message;
import com.cts.model.User;

public interface MessageRepository extends JpaRepository<Message, Integer>{
//List<Message> findAllById(int id);
	
}
