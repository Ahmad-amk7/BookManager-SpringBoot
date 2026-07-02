package com.example.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.Models.BookClass;
import com.example.demo.Models.UserAccount;

@Repository
public interface BookRepository extends JpaRepository<BookClass, String> {
	
	@Query("SELECT b FROM BookClass b WHERE b.userAccount = ?1")
	List<BookClass> findByUserAccount(UserAccount userAccount);

}
