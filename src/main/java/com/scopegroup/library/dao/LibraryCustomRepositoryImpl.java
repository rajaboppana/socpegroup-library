/**
 * 
 */
package com.scopegroup.library.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.scopegroup.library.pojo.Publication;

/**
 * @author Raja
 *
 * Scope Group test
 */
@SuppressWarnings("unchecked")
@Repository("libraryCustomRepositoryImpl")
@Transactional
public class LibraryCustomRepositoryImpl implements ILibraryCustomRepository {

	@PersistenceContext
	EntityManager entityManager;

	
	@Override
	public List<Publication> getBookByisbn(String isbn) {
		List<Publication> publications = null;
		Query query = entityManager.createNativeQuery("SELECT publication.* FROM PUBLICATION publication " + "WHERE publication.isbn LIKE ?", Publication.class);
		query.setParameter(1, isbn + "%");

		try {
			publications = query.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return publications;

	}

	@Override
	public void savePublication(Publication publication) {
		entityManager.merge(publication);	
	}

	@Override
	public List<Publication> findAll() {
		List<Publication> publications = null;
		Query query = entityManager.createNativeQuery("SELECT publication.* FROM PUBLICATION publication", Publication.class);

		try {
			publications = query.getResultList();
		} catch (NoResultException e) {
			e.printStackTrace();
		}

		return publications;
	}

}
