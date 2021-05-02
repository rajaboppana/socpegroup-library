/**
 * 
 */
package com.scopegroup.library.dao;

import java.util.List;

import com.scopegroup.library.pojo.Book;

/**
 * @author Raja
 *
 * Scope Group test
 */
public interface LibraryCustomRepository {
	
	List<Book> findBookByISBN(String isbn);

}
