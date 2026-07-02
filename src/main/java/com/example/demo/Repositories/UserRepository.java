package com.example.demo.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.UserAccount;


@Repository
public interface UserRepository extends JpaRepository<UserAccount, String> {

	@Query("SELECT u FROM UserAccount u WHERE u.UserName = ?1")
	Optional<UserAccount> findByUserName(String userName);

}
