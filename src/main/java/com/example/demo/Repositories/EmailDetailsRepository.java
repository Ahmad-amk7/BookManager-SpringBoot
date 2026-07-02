package com.example.demo.Repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.EmailDetails;
import com.example.demo.Models.UserAccount;


@Repository
public interface EmailDetailsRepository extends JpaRepository<EmailDetails, String> {
	
	@Query("Select e From EmailDetails e Where e.userAccount = ?1")
	Optional<EmailDetails> findByUserAccoun(UserAccount account);
	
	@Query("Select n From EmailDetails n Where n.dEmailPinNumber = ?1")
	List<EmailDetails> findByDEmailPinNumber(String pinNumber);

}
