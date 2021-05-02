/**
 * 
 */
package com.scopegroup.library.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.scopegroup.library.pojo.Book;

/**
 * @author Raja
 *
 * Scope Group test
 */
public class LibraryCustomRepositoryImpl implements LibraryCustomRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public List<Book> findBookByISBN(String isbn) {
		Object result = null;
		List<Book> books = null;
		Query query = entityManager.createNativeQuery("SELECT book.* FROM BOOK book " + "WHERE book.isbn LIKE ?", Book.class);
		query.setParameter(1, isbn + "%");

		try {
			books = query.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();;
		}

		return books;

	}

}
