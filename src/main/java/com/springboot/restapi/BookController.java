package com.springboot.restapi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController    //when we use @RestController no need of @ResponseBody
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	// get  all book  handler
	@GetMapping("/books")   //here we use @GetMapping,@PostMapping,@DeleteMapping,@PutMapping etc. instead of @RequestMapping like MVC.
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> list= this.bookService.allBook();
		if(list.size()<=0)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  //404 
		
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
	// get single book data handler
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBooks(@PathVariable("id") int id) {
		  Book book= bookService.fetchById(id);	
		  if(book==null)
			  return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		  
		  return ResponseEntity.of(Optional.of(book));
	}
	
	@PostMapping("/books")    // insert  data handler
	public ResponseEntity<Book> insertBooks(@RequestBody Book book) {
		try {   
		Book b = this.bookService.addBook(book);
		return ResponseEntity.of(Optional.of(b));
	}catch(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();		
	}
}	
	@DeleteMapping("/books/{id}")  // delete data handler
	  public void deleteBooks(@PathVariable("id") int id) {
		  try {
		  this.bookService.deleteBook(id);
		  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	     }catch(Exception e) {
	    	 e.printStackTrace();
	    	 ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	     }
	  }
	
	@PutMapping( "/books/{id}" )
	public ResponseEntity<Book> updateBook( @RequestBody Book book , @PathVariable("id") int id) {
	try {
		this.bookService.updateBook(book,id);
		return ResponseEntity.ok().body(book);
	}catch(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}

	
}
