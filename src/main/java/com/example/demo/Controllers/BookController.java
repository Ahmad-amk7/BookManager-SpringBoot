package com.example.demo.Controllers;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.DemoBooks1610Application;
import com.example.demo.Models.BookClass;
import com.example.demo.Services.BookService;
import com.example.demo.Services.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping("/")
public class BookController {

	
	private BookService _BookService;
	private UserService _UserService;
    
	
	
	public BookController(BookService _BookService, UserService _UserService) {
		super();
		this._BookService = _BookService;
		this._UserService = _UserService;
	}

	@GetMapping("/getMyBooks")
	public String getMyBookss(
			Model model,
			HttpSession session
			) {
		
		String userId = session.getAttribute("userId").toString();
		List<BookClass> books = new ArrayList<>();
		books = _BookService.SelectBoksByUserId(userId);
		model.addAttribute("books", books);
		session.setAttribute("userId", userId);
		
		return "my_books";
	}
	
	@GetMapping("/createNewBook")
	public String getMethodName(
		     HttpSession session
			) {
		
		String userId = session.getAttribute("userId").toString();
		session.setAttribute("userId", userId);
		
		return "add_new_book";
	}
	
	@GetMapping("/deleteBook/{id}")
	public String getDeleteBookk(
			@PathVariable("id") String id,
			HttpSession session,
			Model model
			) {
		String userId = session.getAttribute("userId").toString();
		session.setAttribute("userId", userId);
		BookClass book = new BookClass();
		book = _BookService.SelectBookByBookId(id);
		model.addAttribute("book", book);
		
		
		return "get_delete_book";
	}
	

	@GetMapping("/updateBook/{id}")
	public String getMethodName(
			@PathVariable("id") String id,
			HttpSession session,
			Model model
			) {
		
		String userId = session.getAttribute("userId").toString();
		BookClass  book = new BookClass();
		book = _BookService.SelectBookByBookId(id);
		model.addAttribute("book", book);
		session.setAttribute("userId", userId);
		
		return "update_book";
	}
	
	
	
	
	// post methods
	
	@PostMapping("/addNewBookBB")
	public String postAddNewBook(
			@RequestParam("BookName") String bookName,
			@RequestParam("BookAuthor") String bookAuthor,
			@RequestParam("BookDate") Date bookDate,
			@RequestParam("BookFile") MultipartFile bookFile,
			@RequestParam("BookImage") MultipartFile bookImage,
			HttpSession session
			
			) {
		String userId = session.getAttribute("userId").toString();
		String bookFileIdName = UUID.randomUUID().toString().substring(0, 8) + bookFile.getOriginalFilename();
		String bookImageIdName = UUID.randomUUID().toString().substring(0, 8) + bookImage.getOriginalFilename();
		String currentDir = System.getProperty("user.dir");
		
		//Path bookFileToSave = Paths.get(currentDir, "/public/files/", bookFileIdName);
		String bookFileToSave = currentDir + "/public/files/" + bookFileIdName;
		String bookFileToData = bookFileIdName;
		
		//Path bookImageToSave = Paths.get(currentDir, "/public/images", bookImageIdName);
		String bookImageToSave = currentDir + "/public/images/"  + bookImageIdName;
		System.out.println("book path is = " + bookImageToSave);
		String bookImageToData =  bookImageIdName;
		
		try(InputStream fileStream = bookFile.getInputStream()) 
		{
			Files.copy(fileStream, Paths.get(bookFileToSave), StandardCopyOption.REPLACE_EXISTING);
			
		} catch (Exception e) {
			System.out.println("the error in post add new book is = " + e.toString());
		}
		
		try(InputStream imageStream = bookImage.getInputStream()) 
		{
			Files.copy(imageStream, Paths.get(bookImageToSave), StandardCopyOption.REPLACE_EXISTING);
			
		} catch (Exception e) {
			System.out.println("the error in post add new book is = " + e.toString());
		}
		
		try {
			String bookId = UUID.randomUUID().toString().substring(0, 8);
			BookClass book = new BookClass();
			book.setBookId(bookId);
			book.setBookName(bookName);
			book.setBookAuthor(bookAuthor);
			book.setBookDate(bookDate);
			book.setBookFile(bookFileToData);
			book.setBookImage(bookImageToData);
			System.out.println("the Book is = " + book.toString());
			_BookService.InsertNewBook(book, userId);
			session.setAttribute("userId", userId);
			
		} catch (Exception e) {
			System.out.println("error is ========  " + e.toString());
		}
		
		
		return "redirect:/getMyBooks";
	}
	
	@Transactional
	@PostMapping("/deleteeBook/{id}")
	public String postMethodName(
			@PathVariable("id") String id,
			HttpSession session
			) {
		BookClass book = new BookClass();
		String currentDir = System.getProperty("user.dir");
		
		if(!id.isEmpty())
		{
			book = _BookService.SelectBookByBookId(id);
			File bookFile = new File(currentDir + "/public/files/" + book.getBookFile());
			if(bookFile.exists())
				bookFile.delete(); 
			
			File imageFile = new File(currentDir + "/public/images/" + book.getBookImage());
			if(imageFile.exists())
				imageFile.delete();
			 
		    _BookService.DeleteBook(id);
		}
		String userId = session.getAttribute("userId").toString();
		session.setAttribute("userId", userId);
		
		return "redirect:/getMyBooks";
	}
	
	
	// Update the Book
	@Transactional
	@PostMapping("/BookToUpdate/{id}")
	public String postUpdateBookkk(
			@PathVariable("id") String id,
			@RequestParam("BookName") String bookName,
			@RequestParam("BookAuthor") String bookAuthor,
			@RequestParam("BookDate") Date bookDate,
			@RequestParam("BookFile") MultipartFile bookFile,
			@RequestParam("BookImage") MultipartFile bookImage,
			HttpSession session
			) {
		
		String userId = session.getAttribute("userId").toString();
		BookClass bookData = new BookClass();
		bookData = _BookService.SelectBookByBookId(id);
		String idName = UUID.randomUUID().toString().substring(0, 8);
		String currentDir = System.getProperty("user.dir");
		String bookFileToData = null;
		String bookImageToData = null;
		String bookFIleNameString = bookFile.getOriginalFilename();
		String bookImageNameString = bookImage.getOriginalFilename();
		System.out.println("the Part is =  " + bookFile + " " + bookImage + "\n the BookFileName is = " + 
		bookFile.getOriginalFilename() + "bookFIleNameString is = " + bookFIleNameString);
		
		
		
		if(bookFIleNameString != "") {
			System.out.println("bookFile not null");
			bookFileToData = idName + bookFile.getOriginalFilename();
			String bookFileDir = currentDir + "/public/files/";
			String bookFileToSave = bookFileDir + bookFileToData;
			String bookFileToDelete = bookFileDir + bookData.getBookFile();
			
			try(InputStream fileStream = bookFile.getInputStream()) 
			{
				Files.copy(fileStream, Paths.get(bookFileToSave), StandardCopyOption.REPLACE_EXISTING);
				//bookData.setBookFile(bookFileToData);
				File file = new File(bookFileToDelete);
				if(file.exists())
					file.delete();
				
			} catch (Exception e) {
				System.out.println("the error in bookStream is = " + e.toString());
			}
			
		}
		
		if(bookImageNameString != "") 
		{ 
			System.out.println("the bookImageNameString is = " + bookImageNameString);
			bookImageToData = idName + bookImage.getOriginalFilename();
			String bookImageDir = currentDir + "/public/images/"; 
			String bookImageToSave = bookImageDir + bookImageToData;
			String bookImageToDelete = bookImageDir + bookData.getBookImage();
			
			try(InputStream imageStream = bookImage.getInputStream()) 
			{
				Files.copy(imageStream, Paths.get(bookImageToSave), StandardCopyOption.REPLACE_EXISTING);
				//bookData.setBookImage(bookImageToData);
				File file = new File(bookImageToDelete);
				if(file.exists())
					file.delete();
				
			} catch (Exception e) {
				System.out.println("the error in imageStream is = " + e.toString());
			}

			
		}
		
		bookData.setBookName(bookName);
		bookData.setBookAuthor(bookAuthor);
		bookData.setBookDate(bookDate);
		if(bookFileToData != null)
		{
			bookData.setBookFile(bookFileToData);
		}else {
			bookData.setBookFile(bookData.getBookFile());
		}
		
		if(bookImageToData != null)
		{
			bookData.setBookImage(bookImageToData);
		}else {
			bookData.setBookImage(bookData.getBookImage());
		}
		//bookData.setBookFile(bookFileToData);
			
		_BookService.UpdateBaook(bookData, id);
		session.setAttribute("userId", userId);
		
		
		return "redirect:/getMyBooks";
	}
	
	
	
	
	
	
	
	

}
