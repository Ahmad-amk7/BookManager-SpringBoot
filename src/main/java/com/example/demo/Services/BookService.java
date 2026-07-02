package com.example.demo.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Models.BookClass;
import com.example.demo.Models.UserAccount;
import com.example.demo.Repositories.BookRepository;
import com.example.demo.Repositories.UserRepository;

@Service
@Transactional
public class BookService implements BookFace {
	
	
	private UserRepository _UserRepository;
	private BookRepository _BookRepository;
	

	public BookService(UserRepository _UserRepository, BookRepository _BookRepository) {
		super();
		this._UserRepository = _UserRepository;
		this._BookRepository = _BookRepository;
	}

	@Override
	public void InsertNewBook(BookClass bookClass, String id) {
		UserAccount account = new UserAccount();
		try 
		{
			if(!id.isEmpty())
			{
				account = _UserRepository.findById(id).orElse(null);
				bookClass.setUserAccount(account);
				_BookRepository.save(bookClass);
			}
			
			
		} catch (Exception e) {
			System.out.println("the error in insertNewBook is = " + e.toString());
		}
		
	}

	@Transactional
	@Override
	public void UpdateBaook(BookClass bookClass, String id) {
		BookClass book = new BookClass();
		try {
			if(!id.isEmpty())
			{
				book = _BookRepository.findById(id).orElse(null);
				book.setBookName(bookClass.getBookName());
				book.setBookAuthor(bookClass.getBookAuthor());
				book.setBookDate(bookClass.getBookDate());
				book.setBookFile(bookClass.getBookFile());
				book.setBookImage(bookClass.getBookImage());
				_BookRepository.save(book);
			}
			
		} catch (Exception e) {
			System.out.println("the error in update Book is = " + e.toString());
		}
		
	}

	@Override
	public void DeleteBook(String id) {
		BookClass book = new BookClass();
		try {
			if(!id.isEmpty())
			{
				book = _BookRepository.findById(id).orElse(null);
				if(book != null)
					_BookRepository.delete(book);
			}
			
		} catch (Exception e) {
			System.out.println("the error in Delete Book is = " + e.toString());
		}
		
	}

	@Override
	public List<BookClass> SelectallBooks() {
		List<BookClass> books = new ArrayList<BookClass>();
		try {
			books = _BookRepository.findAll();
			
		} catch (Exception e) {
			System.out.println("the error in select all Books is = " + e.toString());
		}
		return books;
	}

	@Override
	public List<BookClass> SelectBoksByUserId(String id) {
		UserAccount account = new UserAccount();
		List<BookClass> books = new ArrayList<BookClass>();
		try {
			if(!id.isEmpty())
			{
				account = _UserRepository.findById(id).orElse(null);
				books = _BookRepository.findByUserAccount(account);
			}
			
		} catch (Exception e) {
			System.out.println("the error in select Books by UserId is = " + e.toString());
		}
		return books;
	}

	@Override
	public BookClass SelectBookByBookId(String id) {
		BookClass book = new BookClass();
		try {
			if(!id.isEmpty())
			{
				book = _BookRepository.findById(id).orElse(null);
				
			}
			
		} catch (Exception e) {
			System.out.println("the error in select Book by BookId is = " + e.toString());
		}
		return book;
	}

}
