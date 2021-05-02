/**
 * 
 */
package com.scopegroup.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scopegroup.library.pojo.Book;

/**
 * @author Raja
 *
 * Scope Group test
 */
public interface LibraryRepository extends JpaRepository<Book, Long>, LibraryCustomRepository{
	
}
