package com.example.demo.Models;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "EmailDetails")
public class EmailDetails {
	
	 @Id
	 private String dEmailId;
	 @Column(length = 100, unique = true)
	 private String dEmailResipient;
	 @Column(length = 300)
	 private String dEmailMessageBody;
	 @Column(length = 50)
	 private String dEmailSubject;
	 @Column(length = 10)
	 private String dEmailPinNumber;
	 private LocalDateTime dEmailLocalDateTime;
	 @Column(length = 500)
	 private String dEmailAttachment;
	 
	 @ManyToOne
	 @JoinColumn(name = "userId", referencedColumnName = "UserId")
	 private UserAccount userAccount;

	 public EmailDetails() {
		super();
	 }

	 public EmailDetails(String dEmailId, String dEmailResipient, String dEmailMessageBody, String dEmailSubject,
			String dEmailPinNumber, LocalDateTime dEmailLocalDateTime, String dEmailAttachment,
			UserAccount userAccount) {
		super();
		this.dEmailId = dEmailId;
		this.dEmailResipient = dEmailResipient;
		this.dEmailMessageBody = dEmailMessageBody;
		this.dEmailSubject = dEmailSubject;
		this.dEmailPinNumber = dEmailPinNumber;
		this.dEmailLocalDateTime = dEmailLocalDateTime;
		this.dEmailAttachment = dEmailAttachment;
		this.userAccount = userAccount;
	 }

	 public String getdEmailId() {
		 return dEmailId;
	 }

	 public void setdEmailId(String dEmailId) {
		 this.dEmailId = dEmailId;
	 }

	 public String getdEmailResipient() {
		 return dEmailResipient;
	 }

	 public void setdEmailResipient(String dEmailResipient) {
		 this.dEmailResipient = dEmailResipient;
	 }

	 public String getdEmailMessageBody() {
		 return dEmailMessageBody;
	 }

	 public void setdEmailMessageBody(String dEmailMessageBody) {
		 this.dEmailMessageBody = dEmailMessageBody;
	 }

	 public String getdEmailSubject() {
		 return dEmailSubject;
	 }

	 public void setdEmailSubject(String dEmailSubject) {
		 this.dEmailSubject = dEmailSubject;
	 }

	 public String getdEmailPinNumber() {
		 return dEmailPinNumber;
	 }

	 public void setdEmailPinNumber(String dEmailPinNumber) {
		 this.dEmailPinNumber = dEmailPinNumber;
	 }

	 public LocalDateTime getdEmailLocalDateTime() {
		 return dEmailLocalDateTime;
	 }

	 public void setdEmailLocalDateTime(LocalDateTime dEmailLocalDateTime) {
		 this.dEmailLocalDateTime = dEmailLocalDateTime;
	 }

	 public String getdEmailAttachment() {
		 return dEmailAttachment;
	 }

	 public void setdEmailAttachment(String dEmailAttachment) {
		 this.dEmailAttachment = dEmailAttachment;
	 }

	 public UserAccount getUserAccount() {
		 return userAccount;
	 }

	 public void setUserAccount(UserAccount userAccount) {
		 this.userAccount = userAccount;
	 }

	 



	
	
	 
	 

}
