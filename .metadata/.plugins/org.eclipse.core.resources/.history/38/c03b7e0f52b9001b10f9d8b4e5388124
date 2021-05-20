package com.cts.repository;


	import java.util.List;

	import org.springframework.data.jpa.repository.JpaRepository;


import com.cts.model.Message;
import com.cts.model.User;
	
	public interface UserRepository extends JpaRepository<User, Integer>{
	 User findByMobileNumber(String mobileNumber);
	}