package com.springboot.restapi;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Integer>{
	
	// custom finder method
	
	public Book findById(int id);

	

	

	

}
