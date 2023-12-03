package com.springboot.restapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component                       // this annotation is used because here we used autowiring.
public class BookService {
	
	@Autowired
	private  BookRepository bookRepository;
	
 //private static List<Book> list=new ArrayList<>();
 
   // add book
	public  Book addBook(Book book) {
		   Book result= bookRepository.save(book);
		return result;
	}
    
	// get all book
	public  List<Book> allBook(){
		List<Book> list=(List<Book>) bookRepository.findAll();
		return list;
	}

	// delete book
	public  void deleteBook(int id) {
		
		// 1. list=list.stream().filter(Book -> Book.getId() != id).collect(Collectors.toList());
		
		/* 2. for(int i=0;i<list.size();i++) {
			      if(list.get(i).getId()==id)
				      list.remove(i); 
	      }  */
		
		bookRepository.deleteById(id);
		
	}
     
	// get book by id
	public  Book fetchById(int id) {
	  /*	Book book=null;
		try {
		    for(int i=0;i<list.size();i++) {
			   if(list.get(i).getId()==id) 
			      book= list.get(i);
		    }  
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return book;   */
	
	Book book= bookRepository.findById(id);
	return book;	
}   
		
		
     // update book
	public  void updateBook(Book book, int id) {
	/*	list=list.stream().map(b->{
			if(b.getId()==id) {
				b.setTitle(book.getTitle());
				b.setAuthor(book.getAuthor());
			}
			return b;
		}).collect(Collectors.toList());   */
		
		book.setId(id);
		 bookRepository.save(book);
		
	}

}
