package com.example.demo.Models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Books")
public class BookClass {

	@Id
	private String BookId;
	@Column(length = 25)
	private String BookName;
	@Column(length = 25)
	private String BookAuthor;
	private Date BookDate;
	@Column(length = 600)
	private String BookFile;
	@Column(length = 600)
	private String BookImage;
	
	@ManyToOne
	@JoinColumn(name = "userId", referencedColumnName = "UserId")
	private UserAccount userAccount;

	public BookClass() {
		
	}
	
	

	public BookClass(String bookName, String bookAuthor, Date bookDate, String bookFile, String bookImage) {
		super();
		BookName = bookName;
		BookAuthor = bookAuthor;
		BookDate = bookDate;
		BookFile = bookFile;
		BookImage = bookImage;
	}



	public BookClass(String bookId, String bookName, String bookAuthor, Date bookDate, String bookFile, String bookImage,
			UserAccount userAccount) {
		super();
		BookId = bookId;
		BookName = bookName;
		BookAuthor = bookAuthor;
		BookDate = bookDate;
		BookFile = bookFile;
		BookImage = bookImage;
		this.userAccount = userAccount;
	}

	public String getBookId() {
		return BookId;
	}

	public void setBookId(String bookId) {
		BookId = bookId;
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public String getBookAuthor() {
		return BookAuthor;
	}

	public void setBookAuthor(String bookAuthor) {
		BookAuthor = bookAuthor;
	}

	public Date getBookDate() {
		return BookDate;
	}

	public void setBookDate(Date bookDate) {
		BookDate = bookDate;
	}

	public String getBookFile() {
		return BookFile;
	}

	public void setBookFile(String bookFile) {
		BookFile = bookFile;
	}

	public String getBookImage() {
		return BookImage;
	}

	public void setBookImage(String bookImage) {
		BookImage = bookImage;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}


	
		
	
}
