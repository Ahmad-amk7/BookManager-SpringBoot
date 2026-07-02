package com.example.demo.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "UserStamp")
public class UserStamp {
	
	@Id
	private String StampId;
	@Column(length = 2500)
	private String StampFist;
	@Column(length = 2500)
	private String StampSecond;
	@Column(length = 2500)
	private String StampPlus;
	@Column(length = 2500)
	private String StampLast;
	@Column(length = 2500)
	private String StampSalt;
	
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "UserId")
	private UserAccount userAccount;

	public UserStamp() {
		super();
	}

	public UserStamp(String stampId, String stampFist, String stampSecond, String stampPlus, String stampLast,
			String stampSalt, UserAccount userAccount) {
		super();
		StampId = stampId;
		StampFist = stampFist;
		StampSecond = stampSecond;
		StampPlus = stampPlus;
		StampLast = stampLast;
		StampSalt = stampSalt;
		this.userAccount = userAccount;
	}

	public String getStampId() {
		return StampId;
	}

	public void setStampId(String stampId) {
		StampId = stampId;
	}

	public String getStampFist() {
		return StampFist;
	}

	public void setStampFist(String stampFist) {
		StampFist = stampFist;
	}

	public String getStampSecond() {
		return StampSecond;
	}

	public void setStampSecond(String stampSecond) {
		StampSecond = stampSecond;
	}

	public String getStampPlus() {
		return StampPlus;
	}

	public void setStampPlus(String stampPlus) {
		StampPlus = stampPlus;
	}

	public String getStampLast() {
		return StampLast;
	}

	public void setStampLast(String stampLast) {
		StampLast = stampLast;
	}

	public String getStampSalt() {
		return StampSalt;
	}

	public void setStampSalt(String stampSalt) {
		StampSalt = stampSalt;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
	
	
	
	

}
