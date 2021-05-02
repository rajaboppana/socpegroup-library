/**
 * 
 */
package com.scopegroup.library.service;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

import com.scopegroup.library.pojo.Book;

/**
 * @author Raja
 *
 * Scope Group test
 */
public interface ILibraryService {
	
	public Set<Book> saveFile(MultipartFile file) throws Exception;
	
	public List<Book> getAllBooks();
	
	public List<Book> getBookByIsbn(String isbn); 
	
}
