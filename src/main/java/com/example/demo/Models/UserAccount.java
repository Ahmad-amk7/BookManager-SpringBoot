package com.example.demo.Models;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class UserAccount {
	
	@Id
	@Column(unique = true, length = 100)
	private String userId;
	@Column(unique = true, length = 70)
	private String UserName;
	@Column(length = 70)
	private String UserPass;
	@Column(length = 70)
	private String UserConfirmPass;
	
	private Boolean IsActive;
	
	@OneToMany(targetEntity = BookClass.class, cascade = CascadeType.ALL)
	List<BookClass> bookClass;
	
	@OneToMany(targetEntity = EmailDetails.class, cascade = CascadeType.ALL)
	List<EmailDetails> emailDetails;
	
	@OneToMany(targetEntity = UserStamp.class, cascade = CascadeType.ALL)
	List<UserStamp> userStamps;

	public UserAccount() {
		
	}

	public UserAccount(String userId, String userName, String userPass, String userConfirmPass, boolean isActive,
			List<BookClass> bookClass, List<EmailDetails> emailDetails, List<UserStamp> userStamps) {
		super();
		this.userId = userId;
		UserName = userName;
		UserPass = userPass;
		UserConfirmPass = userConfirmPass;
		IsActive = isActive;
		this.bookClass = bookClass;
		this.emailDetails = emailDetails;
		this.userStamps = userStamps;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getUserPass() {
		return UserPass;
	}

	public void setUserPass(String userPass) {
		UserPass = userPass;
	}

	public String getUserConfirmPass() {
		return UserConfirmPass;
	}

	public void setUserConfirmPass(String userConfirmPass) {
		UserConfirmPass = userConfirmPass;
	}

	public boolean isIsActive() {
		return IsActive;
	}

	public void setIsActive(boolean isActive) {
		IsActive = isActive;
	}

	public List<BookClass> getBookClass() {
		return bookClass;
	}

	public void setBookClass(List<BookClass> bookClass) {
		this.bookClass = bookClass;
	}

	public List<EmailDetails> getEmailDetails() {
		return emailDetails;
	}

	public void setEmailDetails(List<EmailDetails> emailDetails) {
		this.emailDetails = emailDetails;
	}

	public List<UserStamp> getUserStamps() {
		return userStamps;
	}

	public void setUserStamps(List<UserStamp> userStamps) {
		this.userStamps = userStamps;
	}

	

}
