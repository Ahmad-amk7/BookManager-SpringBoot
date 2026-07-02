package com.example.demo.Services;

import java.util.List;

import com.example.demo.Models.BookClass;

public interface BookFace {
	
	void InsertNewBook(BookClass bookClass, String id);
	
	void UpdateBaook(BookClass bookClass, String id);
	
	void DeleteBook(String id);
	
	List<BookClass> SelectallBooks();
	
	List<BookClass> SelectBoksByUserId(String id);
	
	BookClass SelectBookByBookId(String id);

}
