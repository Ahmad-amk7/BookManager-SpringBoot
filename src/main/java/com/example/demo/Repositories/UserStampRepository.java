package com.example.demo.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.UserAccount;
import com.example.demo.Models.UserStamp;


@Repository
public interface UserStampRepository extends JpaRepository<UserStamp, String> {
	
	@Query("Select s From UserStamp s Where s.userAccount = ?1")
	Optional<UserStamp> findByUserAccount(UserAccount userAccount);

}
