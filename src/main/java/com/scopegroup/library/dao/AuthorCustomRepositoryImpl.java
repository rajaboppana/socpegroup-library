/**
 * 
 */
package com.scopegroup.library.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.scopegroup.library.pojo.PublicationDTO;

/**
 * @author Raja
 *
 *         Scope Group test
 */
@Repository("authorRepository")
public class AuthorCustomRepositoryImpl implements IAuthorCustomRepository {

	@PersistenceContext
	EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<PublicationDTO> getPublicationByEmail(String email) throws NoResultException {
		List<Object[]> list = null;
		Query query = entityManager.createNativeQuery(
				"SELECT author.email, author.first_name, publication.isbn, publication.title, publication.description, publication.date \r\n"
						+ "FROM publication as publication, author as author, publication_author as pb_au\r\n"
						+ "WHERE pb_au.email= ? \r\n" + "      AND publication.isbn= pb_au.isbn\r\n"
						+ "      AND author.email = pb_au.email");
		query.setParameter(1, email);

		list = query.getResultList();
		
		List<PublicationDTO> publicationDTOs = new ArrayList<PublicationDTO>();
		
		for(Object[] objects : list) {
			
				PublicationDTO publicationDTO = new PublicationDTO();
				publicationDTO.setEmail(objects[0].toString());
				publicationDTO.setFirstName(objects[1].toString());
				publicationDTO.setIsbn(objects[2].toString());
				publicationDTO.setTitle(objects[3].toString());
				publicationDTO.setDescription(objects[4].toString());
				
				publicationDTOs.add(publicationDTO);
			
		}

		return publicationDTOs;
	}

}
