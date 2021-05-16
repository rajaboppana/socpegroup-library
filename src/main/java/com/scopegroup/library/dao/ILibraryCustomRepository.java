/**
 * 
 */
package com.scopegroup.library.dao;

import java.util.List;

import com.scopegroup.library.pojo.Publication;

/**
 * @author Raja
 *
 * Scope Group test
 */
public interface ILibraryCustomRepository {
	
	List<Publication> getBookByisbn(String isbn);
	void savePublication(Publication publication);
	List<Publication> findAll();

}
